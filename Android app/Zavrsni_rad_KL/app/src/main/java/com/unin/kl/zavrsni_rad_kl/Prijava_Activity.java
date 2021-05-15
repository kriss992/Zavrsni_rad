package com.unin.kl.zavrsni_rad_kl;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
public class Prijava_Activity extends AppCompatActivity implements MojRecyclerViewAdapter1.ItemLongClickListener, OnMapReadyCallback {
    ArrayList<Uri> odabraneSlike = new ArrayList<>();
    String dirSlika;
    Uri slikaUri;
    File f;
    private MojRecyclerViewAdapter1 adapter;
    public GoogleMap mMap;
    LocationManager lm;
    LocationListener ll;
    ImageButton bgal, bfot, bloc;
    Button bsalji;
    TextView tv_lok;
    View prozPogled;
    EditText et_poruka;
    RecyclerView recyclerView;
    List<Address> adrese = null;
    double lat_d, lon_d;
    String adresa="";
    String zupanija="";
    String lat_pr = "";
    String lon_pr = "";
    String vis_pr = "";
    String status;
    int korisnik_id;
    String poruka;
    String tip;
    String slika_p = "ne";
    String datum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);
        getSupportActionBar().setTitle("Obrazac prijave");
        bgal = findViewById(R.id.btngal);
        bfot = findViewById(R.id.btnfot);
        bloc = findViewById(R.id.btnloc);
        bsalji = findViewById(R.id.btnsalji);
        tv_lok = findViewById(R.id.tv_lok);
        et_poruka = findViewById(R.id.poruka);
        prozPogled = findViewById(R.id.poz_view);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapPr1);
        mapFragment.getMapAsync(this);
        recyclerView = findViewById(R.id.rv1);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(Prijava_Activity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new MojRecyclerViewAdapter1(this, odabraneSlike);
        adapter.setLongClickListener(this);
        recyclerView.setAdapter(adapter);
        lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        ll = new LocationListener() {
            @Override
            public void onLocationChanged(Location loc) {
                lat_pr = String.valueOf(loc.convert(loc.getLatitude(), loc.FORMAT_SECONDS));
                lon_pr = String.valueOf(loc.convert(loc.getLongitude(), loc.FORMAT_SECONDS));
                lat_pr = lat_pr.substring(0, lat_pr.length() - 3);
                lon_pr = lon_pr.substring(0, lon_pr.length() - 3);
                vis_pr = String.valueOf(loc.getAltitude());
                lat_d = loc.getLatitude();
                lon_d = loc.getLongitude();
                LatLng moja_lokacija = new LatLng(loc.getLatitude(), loc.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(moja_lokacija).title("TRENUTNA LOKACIJA"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moja_lokacija, 19));
                Geocoder geocoder;
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                double latitude = loc.getLatitude();
                double longitude = loc.getLongitude();
                try {
                    adrese = geocoder.getFromLocation(latitude, longitude, 1);
                    adresa = adrese.get(0).getAddressLine(0);
                    zupanija = adrese.get(0).getAdminArea();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                tv_lok.setText("Vaša trenutna lokacija je:"+"\n"+"Latituda: "+lat_pr+"\n"+"Longituda: "+lon_pr+"\n"
                        +"Visina: "+vis_pr+"\n"+"Adresa je: "+adresa+", "+zupanija);
                lm.removeUpdates(ll);
            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {            }
            @Override
            public void onProviderEnabled(String provider) {            }
            @Override
            public void onProviderDisabled(String provider) {            }
        };
        bloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, ll);
            }
        });
        bfot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                izradiSliku();
            }
        });
        bgal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(Prijava_Activity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED);{
                    ActivityCompat.requestPermissions(Prijava_Activity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                }
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        prozPogled.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    default:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        bsalji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prijavak();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            ClipData clip = data.getClipData();
            if(clip != null) {
                for (int i = 0; i < clip.getItemCount(); i++) {
                    slikaUri = clip.getItemAt(i).getUri();
                    odabraneSlike.add(slikaUri);
                    adapter.notifyDataSetChanged();
                }
            }else{
                slikaUri = data.getData();
                odabraneSlike.add(slikaUri);
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            f = new File(dirSlika);
            slikaUri = Uri.fromFile(f);
            mediaScanIntent.setData(slikaUri);
            this.sendBroadcast(mediaScanIntent);
            odabraneSlike.add(slikaUri);
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_glavna__login, menu);
        MenuItem odjava = menu.findItem(R.id.odjava1);
        MenuItem povijest = menu.findItem(R.id.povijest1);
        if(((Varijable) this.getApplication()).getVar_log().equals("ne")) {
            odjava.setVisible(true);
            povijest.setVisible(true);
        }else {
            odjava.setVisible(false);
            povijest.setVisible(false);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.oprog) {
            Intent inte= new Intent(Prijava_Activity.this, Oprog_Activity.class);
            startActivity(inte);
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(Prijava_Activity.this, Lokacije_Glavna_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.pri || id == R.id.pri2) {
            Intent inte= new Intent(Prijava_Activity.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.postavke) {
            Intent inte= new Intent(Prijava_Activity.this, Postavke_Activity.class);
            startActivity(inte);
        }
        if (id == R.id.odjava1) {
            if(((Varijable) this.getApplication()).getVar_log().equals("ne")) {
                ((Varijable) this.getApplication()).setVar_log("da");
                ((Varijable) this.getApplicationContext()).setVar_user(0);
                ((Varijable) this.getApplicationContext()).setVar_ime("");
                ((Varijable) this.getApplicationContext()).setVar_prezime("");
                ((Varijable) this.getApplicationContext()).setVar_oib("");
                ((Varijable) this.getApplicationContext()).setVar_korisnicko_ime("");
                ((Varijable) this.getApplicationContext()).setVar_lozinka("");
                Intent inte = new Intent(Prijava_Activity.this, Login_Activity.class);
                startActivity(inte);
                finish();
                recreate();
            }
        }
        if(id == R.id.povijest1){
            Intent inte= new Intent(Prijava_Activity.this, PovPrijava_Activity.class);
            startActivity(inte);
        }
        return super.onOptionsItemSelected(item);
    }
    private void izradiSliku() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = slikaFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.unin.kl.zavrsni_rad_kl.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 2);
            }
        }
    }
    private File slikaFile() throws IOException {
        String datum = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String strImeSlike = "JPEG_" + datum + "_";
        File dirPohrana = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File slika = File.createTempFile(
                strImeSlike,  /* prefix */
                ".jpg",         /* suffix */
                dirPohrana      /* datoteka */
        );
        dirSlika = slika.getAbsolutePath();
        return slika;
    }
    @Override
    public void onItemClick(View view, int position) {
        odabraneSlike.remove(position);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        postaviKartu();
    }
    void postaviKartu (){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
    @Override
    public void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
    public void prijavak(){
        datum = new SimpleDateFormat("dd.MM.yyyy. HH:mm.ss").format(new Date());
        status =((Varijable) this.getApplicationContext()).getVar_anon();
        korisnik_id = (((Varijable) this.getApplicationContext()).getVar_user());
        if(status.equals("da")){ korisnik_id = 0; }
        poruka = et_poruka.getText().toString();
        tip = "prijava";
        if((odabraneSlike.size()) != 0){
            slika_p = "da";
        }
        Konektor konektor = new Konektor(this);
        konektor.execute(tip, status, String.valueOf(korisnik_id), String.valueOf(lat_d), String.valueOf(lon_d), adresa, zupanija, poruka,
                String.valueOf(odabraneSlike), slika_p, String.valueOf(datum));
    }
    public void poslano(){
        Intent inte = new Intent(Prijava_Activity.this, Login_Activity.class);
        startActivity(inte);
        finish();
    }
    public void slanje(String por) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Prijava_Activity.this, por, Toast.LENGTH_LONG).show();
            }
        });
    }
}