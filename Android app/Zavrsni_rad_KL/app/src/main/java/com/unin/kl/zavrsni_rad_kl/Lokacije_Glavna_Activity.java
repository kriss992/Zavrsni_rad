package com.unin.kl.zavrsni_rad_kl;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class Lokacije_Glavna_Activity extends AppCompatActivity implements OnMapReadyCallback {
    public TextView lat_tv;
    public TextView long_tv;
    public TextView vis_tv;
    double lat = 0;
    double lon = 0;
    double vis = 0;
    String lat_pr = "";
    String lon_pr = "";
    String vis_pr = "";
    float acc = 0;
    float bear = 0;
    String prov = "";
    float speed = 0;
    long fix_time = 0;
    long fix_pass_time = 0;
    boolean zas1 = false;
    boolean zas2 = false;
    boolean zas3 = false;
    boolean zas4 = false;
    boolean zast1 = false;
    public GoogleMap mMap;
    int lok_up = 0;
    String lat_lon = "";
    String vrijeme = "";
    String vrijeme_sprem = "";
    String def = " ??? ";
    static MojaBP mojaBP;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokacije__glavna_);
        lat_tv = (TextView) findViewById(R.id.lattextView);
        long_tv = (TextView) findViewById(R.id.lontextView);
        vis_tv = (TextView) findViewById(R.id.visinatextView);
        lat_tv.setText(def);
        long_tv.setText(def);
        vis_tv.setText(def);
        mojaBP = new MojaBP(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new LocationListener() {
            public void onLocationChanged(Location loc) {
                if (zas1 == true) {
                    lat_tv.setText(String.valueOf(loc.convert(loc.getLatitude(), loc.FORMAT_SECONDS)));
                    long_tv.setText(String.valueOf(loc.convert(loc.getLongitude(), loc.FORMAT_SECONDS)));
                    vis_tv.setText(String.valueOf(loc.getAltitude()));
                    lat = Double.valueOf(loc.getLatitude());
                    lon = Double.valueOf(loc.getLongitude());
                    vis = Double.valueOf(loc.getAltitude());
                    lat_pr = String.valueOf(loc.convert(loc.getLatitude(), loc.FORMAT_SECONDS));
                    lon_pr = String.valueOf(loc.convert(loc.getLongitude(), loc.FORMAT_SECONDS));
                    vis_pr = String.valueOf(loc.getAltitude());
                    acc = loc.getAccuracy();
                    bear = loc.getBearing();
                    prov = loc.getProvider();
                    speed = loc.getSpeed();
                    fix_time = loc.getTime();
                    fix_pass_time = loc.getElapsedRealtimeNanos();
                    SharedPreferences sp = getSharedPreferences("SP_LOKACIJA_STVARI", MODE_PRIVATE);
                    SharedPreferences.Editor sped = sp.edit();
                    sped.putString("lat", String.valueOf(lat));
                    sped.putString("lon", String.valueOf(lon));
                    sped.putString("vis", String.valueOf(vis));
                    sped.putString("acc", String.valueOf(acc));
                    sped.putString("bear", String.valueOf(bear));
                    sped.putString("prov", String.valueOf(prov));
                    sped.putString("speed", String.valueOf(speed));
                    sped.putString("fix_time", String.valueOf(fix_time));
                    sped.putString("fix_pass_time", String.valueOf(fix_pass_time));
                    sped.commit();
                    LatLng moja_lokacija = new LatLng(lat, lon);
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(moja_lokacija).title("TRENUTNA LOKACIJA"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moja_lokacija, 19));
                    zas4 = true;
                    zas3 = true;
                    if (zas2 == true) {
                        snimanje();
                    }
                } else {
                    lat_tv.setText(" ??? ");
                    long_tv.setText(" ??? ");
                    vis_tv.setText(" ??? ");
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 1));
                    zas4 = false;
                }
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {            }
            public void onProviderEnabled(String provider) {            }
            public void onProviderDisabled(String provider) {            }
        };
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 0, ll);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        postaviKartu();
    }
    void postaviKartu() {
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
    public void klikSnimaj(View v) {
        if (zas1 == false && zas4 == false) {
            Toast.makeText(getApplicationContext(), "NE MOŽETE PRATITI NEMATE LOKACIJU!",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "PRATIM KRETANJE", Toast.LENGTH_SHORT)
                    .show();
            SharedPreferences sp = getSharedPreferences("SPREMANJE_kretanje:", MODE_PRIVATE);
            SharedPreferences.Editor sped = sp.edit();
            sp.edit().clear().commit();
            sped.commit();
            lok_up = 1;
            zas2 = true;
            if (lat != 0 || lon != 0) {
                snimanje();
            }
        }
    }
    public void klikLociraj(View v) {
        if (zast1 == true) {
            zas1 = false;
            Toast.makeText(getApplicationContext(), "NE TRAŽIM LOKACIJU", Toast.LENGTH_SHORT)
                    .show();
            lat_tv.setText(def);
            long_tv.setText(def);
            vis_tv.setText(def);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(0, 0), 1));
            mMap.clear();
            zast1 = false;
        } else {
            Toast.makeText(getApplicationContext(), "TRAŽIM LOKACIJU", Toast.LENGTH_SHORT)
                    .show();
            zast1 = true;
            zas1 = true;
            if(lat != 0 && lon != 0 && zas3 == false){
                LatLng moja_lokacija = new LatLng(lat, lon);
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(moja_lokacija).title("TRENUTNA LOKACIJA"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moja_lokacija, 19));
                lat_tv.setText(lat_pr);
                long_tv.setText(lon_pr);
                vis_tv.setText(vis_pr);
            }
        }
    }
    public void klikStani(View v){
        zas2=false;
        Toast.makeText(getApplicationContext(), "PRESTAO SAM SNIMATI KRETANJE", Toast.LENGTH_SHORT).show();
    }
    public void klikPokazi_RUTU(View v) {
        ArrayList<LatLng> koordList = new ArrayList<LatLng>();
        String pom1 = "prazno";
        LatLng moja_lokacija_zadnja = new LatLng(0, 0);
        LatLng moja_lokacija_prva = new LatLng(0, 0);
        mMap.clear();
        SharedPreferences sp = getSharedPreferences("SPREMANJE_kretanje:", MODE_PRIVATE);
        pom1 = sp.getString("lok_up", "prazno");
        if (pom1 != "prazno") {
            lok_up = Integer.parseInt(pom1);
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            for (int i = 1; i <= lok_up; i++) {
                lat_lon = sp.getString("lat_lon" + Integer.toString(i), "prazno");
                if (lat_lon != "prazno") {
                    String racun[] = lat_lon.split("\\|");
                    lat = Double.valueOf(racun[0]);
                    lon = Double.valueOf(racun[1]);
                    koordList.add(new LatLng(lat, lon));
                    builder.include(new LatLng(lat, lon));
                    if(i==lok_up){ moja_lokacija_zadnja = new LatLng(lat, lon); }
                    if(i==1){ moja_lokacija_prva = new LatLng(lat, lon); }
                }
            }
            LatLngBounds podrZoma = builder.build();
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(moja_lokacija_zadnja)
                    .title("ZADNJA LOKACIJA").icon
                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            mMap.addMarker(new MarkerOptions().position(moja_lokacija_prva)
                    .title("PRVA LOKACIJA").icon
                    (BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            PolylineOptions polylineOptions = new PolylineOptions().width(5).color(Color.RED);
            polylineOptions.addAll(koordList);
            mMap.addPolyline(polylineOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(podrZoma, 50));
        }else{Toast.makeText(getApplicationContext(), "NEMATE RUTU", Toast.LENGTH_SHORT).show();}
    }
    public void klikSpremiSnimano1(View view){
        String pom3 = "";
        Integer id_rut = 0;
        Integer id_lok_rut = 0;
        vrijeme = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(new Date());
        SharedPreferences sp1 = getSharedPreferences("SPREMANJE_kretanje:", MODE_PRIVATE);
        pom3 = sp1.getString("lok_up", "prazno");
        if (pom3 != "prazno"){
            Cursor max_id_RUT = mojaBP.max_id_rut();
            Cursor max_id_RUT_LOK = mojaBP.max_id_rut_lok();
            max_id_RUT.moveToPosition(0);
            max_id_RUT_LOK.moveToPosition(0);
            Integer max_id_rut = max_id_RUT.getInt(0);
            Integer max_id_rut_lok = max_id_RUT_LOK.getInt(0);
            if(max_id_rut == 0){ id_rut=1; }else{id_rut = max_id_rut + 1;}
            if(max_id_rut_lok == 0){ id_lok_rut=1; }else{id_lok_rut = max_id_rut_lok + 1;}
            for (int i = 1; i <= Integer.valueOf(pom3); i++){
                lat_lon = sp1.getString("lat_lon" + Integer.toString(i), "prazno");
                id_lok_rut=i;
                boolean snimljeno_RUT = mojaBP.spremiPodatke_RUT(lat_lon, vrijeme,
                        id_lok_rut, id_rut);
                if(snimljeno_RUT != true){
                    Toast.makeText(getApplicationContext(),
                            "ERROR: NISTE SPREMILI PODATKE U BP", Toast.LENGTH_SHORT).show();
                }
            }
        }else{Toast.makeText(getApplicationContext(),
                "NEMATE RUTU ZA SPREMITI!", Toast.LENGTH_SHORT).show();}
    }
    void snimanje(){
        SharedPreferences sp = getSharedPreferences("SPREMANJE_kretanje:", MODE_PRIVATE);
        SharedPreferences.Editor sped = sp.edit();
        sped.putString("lok_up",Integer.toString(lok_up));
        lat_lon = String.valueOf(lat)+"|"+String.valueOf(lon);
        sped.putString("lat_lon" + Integer.toString(lok_up), String.valueOf(lat_lon));
        lok_up++;
        sped.commit();
    }
    public void klikPokazSveSnimano(View v){
        Intent inte= new Intent(Lokacije_Glavna_Activity.this, Lokacije_PovKret_Activity.class);
        startActivity(inte);
    }
    public void klikLocirajPokaziSpr(View v){
        Intent inte1= new Intent(Lokacije_Glavna_Activity.this, Lokacije_Pov_Activity.class);
        startActivity(inte1);
    }
    public void klikLocirajSpremi(View v){
        vrijeme_sprem = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(new Date());
        if(zas4 == true){
            String lat_s = String.valueOf(lat);
            String lon_s = String.valueOf(lon);
            String vis_s = String.valueOf(vis);
            String acc_s = String.valueOf(acc);
            String bear_s = String.valueOf(bear);
            String prov_s = String.valueOf(prov);
            String speed_s = String.valueOf(speed);
            String fix_time_s = String.valueOf(fix_time);
            String fix_pass_time_s = String.valueOf(fix_pass_time);
            String fix_time_s_f = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss")
                    .format(fix_time);
            boolean snimljeno = mojaBP.spremiPodatke_LOK(lat_s, lon_s, vis_s,
                    vrijeme_sprem, acc_s, bear_s, prov_s,speed_s, fix_time_s, fix_time_s_f,
                    fix_pass_time_s);
            if(snimljeno != true){
                Toast.makeText(getApplicationContext(), "ERROR: NISTE SPREMILI PODATKE U BP",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            lat_tv.setText(def);
            long_tv.setText(def);
            vis_tv.setText(def);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_glavna__lokacije_, menu);
        return true;}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.oprog) {
            Intent inte= new Intent(Lokacije_Glavna_Activity.this, Oprog_Activity.class);
            startActivity(inte);
        }
        if (id == R.id.pri || id == R.id.pri2) {
            Intent inte= new Intent(Lokacije_Glavna_Activity.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}