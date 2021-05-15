package com.unin.kl.zavrsni_rad_kl;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.ArrayList;
public class Lokacije_PovKret_Activity extends AppCompatActivity implements OnMapReadyCallback{
    boolean zas_b = false;
    public GoogleMap mMap;
    double lat = 0;
    double lon = 0;
    String broj_spremanja_str = "0";
    String del_id = "";
    String sh_id = "";
    int broj_spremanja = 0;
    boolean zas1 = false;
    ListView popisLV_lok;
    ArrayList<String> mojiPodaci;
    ArrayAdapter<String> mojAdapterLok1;
    static MojaBP mojaBP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokacije__pov_kret_);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        popisLV_lok = (ListView) findViewById(R.id.listView2);
        mojaBP = new MojaBP(this);
        mojiPodaci = new ArrayList<String>();
        mojAdapterLok1 = new MojAdapterLok1(this, R.layout.moj_layout_lokacije_pov1, mojiPodaci);
        popisLV_lok.setAdapter(mojAdapterLok1);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        popisLV_lok.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                broj_spremanja_str = String.valueOf(parent.getItemIdAtPosition(position));
                broj_spremanja = Integer.parseInt(broj_spremanja_str) + 1;
                pokazi2();
            }
        });
        popisLV_lok.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                broj_spremanja_str = String.valueOf(parent.getItemIdAtPosition(position));
                broj_spremanja = Integer.parseInt(broj_spremanja_str) + 1;
                final ArrayList<String> mojePolje = new ArrayList<String>();
                mojePolje.add("IZBRIŠI");
                CharSequence[] tmp;
                tmp = mojePolje.toArray(new CharSequence[mojePolje.size()]);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Lokacije_PovKret_Activity.this);
                builder1.setTitle("Izbor");
                builder1.setItems(tmp, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mojePolje.get(which) == "IZBRIŠI") {
                            obrisiPritisnuto();
                        }
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
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    }
    @Override
    protected void onStart() {
        super.onStart();
        pokazi1();
        zas1 = true;
    }
    public void pokazi1(){
        Cursor podaci_RUT = mojaBP.dohvatiPodatke_RUT_list();
        if (podaci_RUT.getCount() == 0) {
            mojiPodaci.clear();
            mojAdapterLok1.notifyDataSetChanged();
            if(zas1==true){
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 1));
                mMap.clear();
            }
            return;
        }
        mojiPodaci.clear();
        while (podaci_RUT.moveToNext()){
            String pod_rut = podaci_RUT.getString(1)+"?"+podaci_RUT.getString(0);
            mojiPodaci.add(pod_rut);
            mojAdapterLok1.notifyDataSetChanged();
        }
    }
    public void pokazi2(){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 1));
        mMap.clear();
        ArrayList<LatLng> koordList = new ArrayList<LatLng>();
        LatLng moja_lokacija_zadnja = new LatLng(0, 0);
        LatLng moja_lokacija_prva = new LatLng(0, 0);
        Object item = popisLV_lok.getItemAtPosition(broj_spremanja - 1);
        String value = item.toString();
        String split1[] = value.split("\\?");
        sh_id = split1[0];
        Cursor max_id_RUT_LOK2 = mojaBP.max_id_rut_LOK2(sh_id);
        max_id_RUT_LOK2.moveToPosition(0);
        Integer max_id_rut_lok2 = max_id_RUT_LOK2.getInt(0);
        Cursor podaci_RUT;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for(int i=1; i<=max_id_rut_lok2; i++){
            podaci_RUT = mojaBP.dohvatiPodatke_RUT_pojedino(sh_id,String.valueOf(i));
            podaci_RUT.moveToPosition(0);
            String ko = podaci_RUT.getString(3);
            String racun[] = ko.split("\\|");
            lat = Double.valueOf(racun[0]);
            lon = Double.valueOf(racun[1]);
            koordList.add(new LatLng(lat, lon));
            builder.include(new LatLng(lat, lon));
            if(i==max_id_rut_lok2){ moja_lokacija_zadnja = new LatLng(lat, lon); }
            if(i==1){ moja_lokacija_prva = new LatLng(lat, lon); }
        }
        mMap.addMarker(new MarkerOptions().position(moja_lokacija_zadnja).title("ZADNJA LOKACIJA").icon
                (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.addMarker(new MarkerOptions().position(moja_lokacija_prva).title("PRVA LOKACIJA").icon
                (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        PolylineOptions polylineOptions = new PolylineOptions().width(5).color(Color.RED);
        polylineOptions.addAll(koordList);
        mMap.addPolyline(polylineOptions);
        LatLngBounds podrZoma = builder.build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(podrZoma, 50));
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
    public void klikNe(){ zas_b = false;}
    public void klikDa(){
        if(zas_b == false) {
            Object item = popisLV_lok.getItemAtPosition(broj_spremanja - 1);
            String value = item.toString();
            String split1[] = value.split("\\?");
            del_id = split1[0];
            Integer deleteRut = mojaBP.obrisiStavku_RUT(String.valueOf(del_id));
        }
        if(zas_b == true){
            zas_b = false;
            mojaBP.obrisiPodatke_RUT();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 1));
        mMap.clear();
        pokazi1();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lokacije_pov_kret, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.oprog) {
            Intent inte= new Intent(Lokacije_PovKret_Activity.this, Oprog_Activity.class);
            inte.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inte);
            finish();
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(Lokacije_PovKret_Activity.this, Lokacije_Glavna_Activity.class);
            inte.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inte);
            finish();
        }
        if (id == R.id.pri || id == R.id.pri2) {
            Intent inte= new Intent(Lokacije_PovKret_Activity.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.brisi_lok_pov_kre) {
            zas_b = true;
            obrisiPritisnuto();
        }
        return super.onOptionsItemSelected(item);
    }
}