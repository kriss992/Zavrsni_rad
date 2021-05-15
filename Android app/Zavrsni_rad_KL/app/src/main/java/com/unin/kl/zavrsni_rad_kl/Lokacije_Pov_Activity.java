package com.unin.kl.zavrsni_rad_kl;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;

public class Lokacije_Pov_Activity extends AppCompatActivity implements OnMapReadyCallback {
    public GoogleMap mMap;
    String broj_spremanja_str = "0";
    String del_id = "";
    int broj_spremanja = 0;
    ListView popisLV1;
    ArrayList<String> mojiPodaci1;
    ArrayAdapter<String> mojAdapterLok2;
    boolean zast=false;
    static MojaBP mojaBP;
    private static Lokacije_Pov_Activity sLokacije_Pov_Activity;
    boolean zas_b = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokacije__pov_);
        Toolbar toolbar3 = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        mojaBP = new MojaBP(this);
        popisLV1 = (ListView) findViewById(R.id.listView_lokacije);
        mojiPodaci1 = new ArrayList<String>();
        mojAdapterLok2 = new MojAdapterLok2(this, R.layout.moj_layout_lokacije_pov2,
                mojiPodaci1);
        popisLV1.setAdapter(mojAdapterLok2);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map3);
        mapFragment.getMapAsync(this);
        sLokacije_Pov_Activity = this;
        popisLV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                broj_spremanja_str = String.valueOf(parent.getItemIdAtPosition(position));
                broj_spremanja = Integer.parseInt(broj_spremanja_str);
                pokazi2();
            }
        });
        popisLV1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                broj_spremanja_str = String.valueOf(parent.getItemIdAtPosition(position));
                broj_spremanja = Integer.parseInt(broj_spremanja_str);
                final ArrayList<String> mojePolje=new ArrayList<String>();
                mojePolje.add("POSTAVI KAO METU 1");
                mojePolje.add("POSTAVI KAO METU 2");
                mojePolje.add("IZBRIŠI");
                CharSequence[] tmp;
                tmp = mojePolje.toArray(new CharSequence[mojePolje.size()]);
                AlertDialog.Builder builder1=new AlertDialog.Builder(Lokacije_Pov_Activity.this);
                builder1.setTitle("Izbor");
                builder1.setItems(tmp, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mojePolje.get(which)=="IZBRIŠI"){ obrisiPritisnuto(); }
                        if(mojePolje.get(which)=="POSTAVI KAO METU 1"){ sp_tar1(); }
                        if(mojePolje.get(which)=="POSTAVI KAO METU 2"){ sp_tar2(); }
                    }
                });
                builder1.setCancelable(false);
                builder1.setPositiveButton("Zatvori", null);
                AlertDialog alert = builder1.create();
                builder1.show();
                return true;
            }
        });
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
    protected void onStart() {
        super.onStart();
        pokazi1();
        zast=false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lokacije_pov, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.oprog) {
            Intent inte= new Intent(Lokacije_Pov_Activity.this, Oprog_Activity.class);
            inte.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inte);
            finish();
        }
        if (id == R.id.comp) {
            SharedPreferences sp2 = getSharedPreferences("SP_USPOREDBA", MODE_PRIVATE);
            String pom1 = sp2.getString("id1", "prazno");
            String pom2 = sp2.getString("id2", "prazno");
            if(pom1.equals("prazno") || pom2.equals("prazno")) {
                Toast.makeText(getApplicationContext(), "MORATE DODATI DVIJE METE!",
                        Toast.LENGTH_SHORT).show();
            }else{
                Intent inte = new Intent(Lokacije_Pov_Activity.this,
                        Lokacije_comp.class);
                startActivity(inte);
            }
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(Lokacije_Pov_Activity.this,
                    Lokacije_Glavna_Activity.class);
            inte.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inte);
            finish();
        }
        if (id == R.id.pri || id == R.id.pri2) {
            Intent inte= new Intent(Lokacije_Pov_Activity.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.brisi_lok_pov) {
            zas_b = true;
            obrisiPritisnuto();
        }
        return super.onOptionsItemSelected(item);
    }
    public static Lokacije_Pov_Activity getInstance() { return sLokacije_Pov_Activity; }
    public void pokazi1(){
        Cursor podaci_LOK = mojaBP.dohvatiPodatke_LOK();
        if (podaci_LOK.getCount() == 0) {
            mojiPodaci1.clear();
            mojAdapterLok2.notifyDataSetChanged();
            if(zast==true){
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 1));
                mMap.clear();
            }
            return;
        }
        mojiPodaci1.clear();
        while (podaci_LOK.moveToNext()){
            String pod = podaci_LOK.getString(0) + "*" +
                    podaci_LOK.getString(1) + "&" +
                    podaci_LOK.getString(2) + "?" + podaci_LOK.getString(10);
            mojiPodaci1.add(pod);
            mojAdapterLok2.notifyDataSetChanged();
        }
    }
    public void pokazi2(){
        Cursor podaci_LOK = mojaBP.dohvatiPodatke_LOK();
        podaci_LOK.moveToPosition(broj_spremanja);
        String lat = podaci_LOK.getString(1);
        String lon = podaci_LOK.getString(2);
        String vrijeme = podaci_LOK.getString(10);
        LatLng moja_lokacija = new LatLng(Double.valueOf(lat), Double.valueOf(lon));
        mMap .clear();
        mMap.addMarker(new MarkerOptions().position(moja_lokacija).title("TRENUTNA LOKACIJA " +
                vrijeme));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moja_lokacija, 19));
    }
    public void obrisiPritisnuto() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Upozorenje").setMessage("Da li želite izbrisati?");
        builder.setCancelable(false);
        builder.setNegativeButton("NE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                klikNe();
            }
        });
        builder.setPositiveButton("DA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                klikDa();
            }
        });
        AlertDialog poruka=builder.create();
        poruka.show();
    }
    public void klikNe(){ zas_b = false; }
    public void klikDa(){
        if(zas_b == false) {
            Object item = popisLV1.getItemAtPosition(broj_spremanja);
            String value = item.toString();
            String split1[] = value.split("\\*");
            del_id = split1[0];
            Integer deleteRed = mojaBP.obrisiStavku_LOK(String.valueOf(del_id));
            SharedPreferences sp2 = getSharedPreferences("SP_USPOREDBA", MODE_PRIVATE);
            String pom1 = sp2.getString("id1", "prazno");
            String pom2 = sp2.getString("id2", "prazno");
            if(pom1.equals(del_id)){
                SharedPreferences.Editor sped2 = sp2.edit();
                sped2.putString("id1","prazno");
                sped2.commit();
            }
            if(pom2.equals(del_id)){
                SharedPreferences.Editor sped2 = sp2.edit();
                sped2.putString("id2","prazno");
                sped2.commit();
            }
        }
        if(zas_b == true){
            zas_b = false;
            mojaBP.obrisiPodatke_LOK();
            SharedPreferences sp = getSharedPreferences("SP_USPOREDBA", MODE_PRIVATE);
            SharedPreferences.Editor sped = sp.edit();
            sped.putString("id1","prazno");
            sped.commit();
            SharedPreferences sp1 = getSharedPreferences("SP_USPOREDBA", MODE_PRIVATE);
            SharedPreferences.Editor sped1 = sp1.edit();
            sped1.putString("id1","prazno");
            sped1.commit();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 1));
        mMap.clear();
        pokazi1();
    }
    public void sp_tar1(){
        Cursor podaci_LOK = mojaBP.dohvatiPodatke_LOK();
        podaci_LOK.moveToPosition(broj_spremanja);
        String id1 = podaci_LOK.getString(0);
        SharedPreferences sp = getSharedPreferences("SP_USPOREDBA", MODE_PRIVATE);
        SharedPreferences.Editor sped = sp.edit();
        sped.putString("id1",id1);
        sped.commit();
    }
    public void sp_tar2(){
        Cursor podaci_LOK = mojaBP.dohvatiPodatke_LOK();
        podaci_LOK.moveToPosition(broj_spremanja);
        String id2 = podaci_LOK.getString(0);
        SharedPreferences sp1 = getSharedPreferences("SP_USPOREDBA", MODE_PRIVATE);
        SharedPreferences.Editor sped1 = sp1.edit();
        sped1.putString("id2",id2);
        sped1.commit();
    }
}