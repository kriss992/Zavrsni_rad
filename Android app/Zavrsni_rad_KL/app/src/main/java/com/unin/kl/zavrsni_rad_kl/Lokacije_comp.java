package com.unin.kl.zavrsni_rad_kl;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
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
public class Lokacije_comp extends AppCompatActivity implements OnMapReadyCallback {
    public GoogleMap mMap;
    String lat1_s = "****";
    String lon1_s = "****";
    String vis1_s = "****";
    String acc1_s = "****";
    String smj1_s = "****";
    String izv1_s = "****";
    String brz1_s = "****";
    String vri1_s = "****";
    String vri_f1_s = "****";
    String star1_s = "****";
    String lat2_s = "****";
    String lon2_s = "****";
    String vis2_s = "****";
    String acc2_s = "****";
    String smj2_s = "****";
    String izv2_s = "****";
    String brz2_s = "****";
    String vri2_s = "****";
    String vri_f2_s = "****";
    String star2_s = "****";
    String dist_s = "****";
    String bear1_s = "****";
    String bear2_s = "****";
    String raz_vis_s ="****";
    String id1_str = "****";
    String id2_str = "****";
    LatLng moja_lokacija1 = new LatLng(0, 0);
    LatLng moja_lokacija2 = new LatLng(0, 0);
    static MojaBP mojaBP;
    ArrayList<String> podaci;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokacije_comp);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map7);
        mapFragment.getMapAsync(Lokacije_comp.this);
        mojaBP = new MojaBP(this);
        SharedPreferences sp = getSharedPreferences("SP_USPOREDBA", MODE_PRIVATE);
        id1_str = sp.getString("id1", "prazno");
        id2_str = sp.getString("id2", "prazno");
        if(id1_str != "prazno"){
        Cursor podaci_LOK = mojaBP.dohvatiPodatke_LOK_pojedino(id1_str);
        podaci_LOK.moveToPosition(0);
        lat1_s = podaci_LOK.getString(1);
        lon1_s = podaci_LOK.getString(2);
        vis1_s = podaci_LOK.getString(3);
        acc1_s = podaci_LOK.getString(5);
        smj1_s = podaci_LOK.getString(6);
        izv1_s = podaci_LOK.getString(7);
        brz1_s = podaci_LOK.getString(8);
        vri1_s = podaci_LOK.getString(9);
        vri_f1_s = podaci_LOK.getString(10);
        star1_s = podaci_LOK.getString(11);
        }
        if(id2_str != "prazno"){
        Cursor podaci_LOK = mojaBP.dohvatiPodatke_LOK_pojedino(id2_str);
        podaci_LOK.moveToPosition(0);
        lat2_s = podaci_LOK.getString(1);
        lon2_s = podaci_LOK.getString(2);
        vis2_s = podaci_LOK.getString(3);
        acc2_s = podaci_LOK.getString(5);
        smj2_s = podaci_LOK.getString(6);
        izv2_s = podaci_LOK.getString(7);
        brz2_s = podaci_LOK.getString(8);
        vri2_s = podaci_LOK.getString(9);
        vri_f2_s = podaci_LOK.getString(10);
        star2_s = podaci_LOK.getString(11);
        }
        podaci=new ArrayList<String>();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,podaci);
        ListView lv=(ListView) this.findViewById(R.id.listView_comp);
        lv.setAdapter(adapter);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        postaviKartu();
    }
    void postaviKartu (){
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        glavni();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_glavna__lokacije_, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.oprog) {
            Intent inte= new Intent(Lokacije_comp.this, Oprog_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(Lokacije_comp.this, Lokacije_Glavna_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.pri || id == R.id.pri2) {
            Intent inte= new Intent(Lokacije_comp.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void glavni(){
        mMap.clear();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 1));
        mMap.clear();
        if(id1_str != "prazno") {
            moja_lokacija1 = new LatLng(Double.valueOf(lat1_s), Double.valueOf(lon1_s));
            mMap.addMarker(new MarkerOptions().position(moja_lokacija1).title("LOKACIJA 1 " + vri_f1_s).icon
                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moja_lokacija1, 19));
        }
        if(id2_str != "prazno"){
            moja_lokacija2 = new LatLng(Double.valueOf(lat2_s), Double.valueOf(lon2_s));
            mMap.addMarker(new MarkerOptions().position(moja_lokacija2).title("LOKACIJA 2 " + vri_f2_s).icon
                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moja_lokacija2, 19));
        }
        if(id1_str != "prazno" && id2_str != "prazno"){
            Location dest1 = new Location("Dest1");
            dest1.setLatitude(Double.valueOf(lat1_s));
            dest1.setLongitude(Double.valueOf(lon1_s));
            dest1.setAltitude(Double.valueOf(vis1_s));
            Location dest2 = new Location("Dest2");
            dest2.setLatitude(Double.valueOf(lat2_s));
            dest2.setLongitude(Double.valueOf(lon2_s));
            dest2.setAltitude(Double.valueOf(vis2_s));
            double vis1 = Double.valueOf(vis1_s);
            double vis2 = Double.valueOf(vis2_s);
            double bear1 = dest1.bearingTo(dest2);
            double bear2 = dest2.bearingTo(dest1);
            float dist = dest1.distanceTo(dest2);
            if(bear1 < 0){bear1 = bear1 +360;}
            if(bear2 < 0){bear2 = bear2 +360;}
            if(vis1 > vis2){raz_vis_s=String.valueOf(vis1-vis2);}
            if(vis2 > vis1){raz_vis_s=String.valueOf(vis2-vis1);}
            dist_s = String.valueOf(dist);
            bear1_s = String.valueOf(bear1);
            bear2_s = String.valueOf(bear2);
            LatLngBounds podrZoma = new LatLngBounds.Builder().include(moja_lokacija1).include(moja_lokacija2).build();
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(podrZoma, 50));
            PolylineOptions polylineOptions = new PolylineOptions().width(5).color(Color.RED);
            polylineOptions.add(moja_lokacija1).add(moja_lokacija2);
            mMap.addPolyline(polylineOptions);
        }
        Location location1 = new Location("");
        location1.setLatitude(Double.parseDouble(lat1_s));
        location1.setLongitude(Double.parseDouble(lon1_s));
        Location location2 = new Location("");
        location2.setLatitude(Double.parseDouble(lat2_s));
        location2.setLongitude(Double.parseDouble(lon2_s));
        podaci.add("LOKACIJA 1");
        podaci.add("LATITUDA: " + String.valueOf(location1.convert(location1.getLatitude(),location1.FORMAT_SECONDS)));
        podaci.add("LONGITUDA: " + String.valueOf(location1.convert(location1.getLongitude(),location1.FORMAT_SECONDS)));
        podaci.add("VISINA: " + vis1_s + " m");
        podaci.add("PRECIZNOST LOKACIJE: " + acc1_s + " m");
        podaci.add("AZIMUT: " + smj1_s + "°");
        podaci.add("IZVOR LOKACIJE: " + izv1_s);
        podaci.add("BRZINA: " + brz1_s + " m/s");
        podaci.add("VRIJEME OSTVARIVANJA LOKACIJE: " + vri_f1_s);
        podaci.add("LOKACIJA 2");
        podaci.add("LATITUDA: " + String.valueOf(location2.convert(location2.getLatitude(),location2.FORMAT_SECONDS)));
        podaci.add("LONGITUDA: " + String.valueOf(location2.convert(location2.getLongitude(),location2.FORMAT_SECONDS)));
        podaci.add("VISINA: "+vis2_s + " m");
        podaci.add("PRECIZNOST LOKACIJE: "+acc2_s + " m");
        podaci.add("AZIMUT: "+smj2_s + "°");
        podaci.add("IZVOR LOKACIJE: "+izv2_s);
        podaci.add("BRZINA: " + brz2_s + " m/s");
        podaci.add("VRIJEME OSTVARIVANJA LOKACIJE: " + vri_f2_s);
        podaci.add("ODNOS DVIJE LOKACIJE");
        podaci.add("AZIMUT 1. METE U ODNOSU NA 2. JE: " + bear1_s + "°");
        podaci.add("AZIMUT 2. METE U ODNOSU NA 1. JE: " + bear2_s + "°");
        podaci.add("UDALJENOST IZMEĐU LOKACIJA JE: " + dist_s + " m");
        podaci.add("VISINSKA RAZLIKA LOKACIJA JE: " + raz_vis_s + " m");
        adapter.notifyDataSetChanged();
    }
}