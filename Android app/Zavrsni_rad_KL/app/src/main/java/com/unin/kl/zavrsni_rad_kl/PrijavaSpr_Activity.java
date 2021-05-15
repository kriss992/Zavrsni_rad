package com.unin.kl.zavrsni_rad_kl;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import android.content.Intent;
        import android.location.Location;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.TextView;
        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.MapFragment;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;
        import java.util.ArrayList;
public class PrijavaSpr_Activity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayList<String > odabranaPrijava = new ArrayList<>();
    ArrayList<String > ucitaneSlikeArray = new ArrayList<>();
    ArrayList<String > pojedineUcitaneSlikeArray = new ArrayList<>();
    TextView tvDatumNaslov, tvDatum, tvLokKordNaslov, tvLokKord, tvLokAddNaslov, tvLokAdd,
            tvPoruka, tvOdgovor;
    View prozMapPogled;
    RecyclerView recyclerView;
    private MojRecyclerViewAdapter3 adapter;
    public GoogleMap mMap;
    boolean map_a = false;
    double lat_d = 0;
    double lon_d = 0;
    String lat_s = "";
    String lon_s = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava_spr);
        getSupportActionBar().setTitle("Prijava");
        tvDatumNaslov = findViewById(R.id.tv_datum_naslov);
        tvDatum = findViewById(R.id.tv_datum);
        tvLokKordNaslov = findViewById(R.id.tv_lok_kord_naslov);
        tvLokAdd = findViewById(R.id.tv_lok_add);
        tvLokAddNaslov = findViewById(R.id.tv_lok_add_naslov);
        tvLokKord = findViewById(R.id.tv_lok_kord);
        tvPoruka = findViewById(R.id.tv_poruka);
        tvOdgovor = findViewById(R.id.tv_odgovor);
        prozMapPogled = findViewById(R.id.proz_map_view);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapPr3);
        mapFragment.getMapAsync(this);
        recyclerView = findViewById(R.id.rv3);
        LinearLayoutManager horizontalLayoutManager
                = new LinearLayoutManager(PrijavaSpr_Activity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManager);
        adapter = new MojRecyclerViewAdapter3(this, pojedineUcitaneSlikeArray);
        recyclerView.setAdapter(adapter);
        odabranaPrijava = ((Varijable) this.getApplication()).getVar_podaci();
        postaviSve();
        prozMapPogled.setOnTouchListener(new View.OnTouchListener() {
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
            Intent inte= new Intent(PrijavaSpr_Activity.this, Oprog_Activity.class);
            startActivity(inte);
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(PrijavaSpr_Activity.this, Lokacije_Glavna_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.pri || id == R.id.pri2) {
            Intent inte= new Intent(PrijavaSpr_Activity.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.postavke) {
            Intent inte= new Intent(PrijavaSpr_Activity.this, Postavke_Activity.class);
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
                Intent inte = new Intent(PrijavaSpr_Activity.this, Login_Activity.class);
                startActivity(inte);
                finish();
                recreate();
            }
        }
        if(id == R.id.povijest1){
            Intent inte= new Intent(PrijavaSpr_Activity.this, PovPrijava_Activity.class);
            startActivity(inte);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        postaviKartu ();
    }
    void postaviKartu (){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        if(map_a == true){ pokaziLok(); }
    }
    public void postaviSve(){
        tvDatum.setText(odabranaPrijava.get(3));
        if(odabranaPrijava.get(10).equals("")){
            tvOdgovor.setVisibility(View.GONE);
        }else{
            tvOdgovor.setText(odabranaPrijava.get(10));
        }
        if(odabranaPrijava.get(8).equals("")){
            tvPoruka.setVisibility(View.GONE);
        }else{
            tvPoruka.setText(odabranaPrijava.get(8));
        }
        if(odabranaPrijava.get(9).equals("da")) {
            ucitaneSlikeArray = ((Varijable) this.getApplication()).getVar_slike();
            for(int i = 0; i < ucitaneSlikeArray.size(); i++){
                pojedineUcitaneSlikeArray.add(ucitaneSlikeArray.get(i));
                adapter.notifyDataSetChanged();
            }
        }else{
            recyclerView.setVisibility(View.GONE);
            tvDatumNaslov.offsetTopAndBottom(10);
        }
        map_a = true;
    }
    public void pokaziLok(){
        if(odabranaPrijava.get(4).equals("0.0") && odabranaPrijava.get(5).equals("0.0")){
            tvLokKordNaslov.setVisibility(View.GONE);
            tvLokKord.setVisibility(View.GONE);
            tvLokAddNaslov.setVisibility(View.GONE);
            tvLokAdd.setVisibility(View.GONE);
            View fragmentMap = this.findViewById(R.id.mapPr3);
            fragmentMap.setVisibility(View.GONE);
            prozMapPogled.setVisibility(View.GONE);
        }else{
            lat_d = Double.parseDouble(odabranaPrijava.get(4));
            lon_d = Double.parseDouble(odabranaPrijava.get(5));
            LatLng moja_lokacija = new LatLng(lat_d, lon_d);
            lat_s = String.valueOf(Location.convert(lat_d, Location.FORMAT_SECONDS));
            lon_s = String.valueOf(Location.convert(lon_d, Location.FORMAT_SECONDS));
            lat_s = lat_s.substring(0, lat_s.length() - 3);
            lon_s = lon_s.substring(0, lon_s.length() - 3);
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(moja_lokacija).title("LOKACIJA PRIJAVE"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moja_lokacija, 19));
            if(odabranaPrijava.get(6).equals("") && odabranaPrijava.get(7).equals("")){
                tvLokKord.setText("Latituda: "+lat_s+"\n"+"Longituda: "+lon_s);
            }else{
                tvLokKord.setText("Latituda: "+lat_s+"\n"+"Longituda: "+lon_s);
                tvLokAdd.setText(odabranaPrijava.get(6)+", "+odabranaPrijava.get(7));
            }
        }
    }
}