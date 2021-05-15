package com.unin.kl.zavrsni_rad_kl;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
public class PovPrijava_Activity extends AppCompatActivity implements MojRecyclerViewAdapter2.ItemClickListener {
    ArrayList<String > podaciArray = new ArrayList<>();
    ArrayList<String > slikeArray = new ArrayList<>();
    ArrayList<String > odabraneStavke = new ArrayList<>();
    ArrayList<String > odabranaPrijava = new ArrayList<>();
    private MojRecyclerViewAdapter2 adapter;
    String tip = "";
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pov_prijava);
        getSupportActionBar().setTitle("Povijest prijava");
        povijestPrijava(String.valueOf(((Varijable) this.getApplication()).getVar_user()));
        RecyclerView recyclerView = findViewById(R.id.rv2);
        @SuppressLint("WrongConstant")
        LinearLayoutManager vertikalLayoutManager = new LinearLayoutManager(PovPrijava_Activity.this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(vertikalLayoutManager);
        adapter = new MojRecyclerViewAdapter2(this, odabraneStavke);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_glavna__login, menu);
        MenuItem odjava = menu.findItem(R.id.odjava1);
        MenuItem povijest = menu.findItem(R.id.povijest1);
        if(((Varijable) this.getApplication()).getVar_log().equals("ne")) {
            odjava.setVisible(true);
            povijest.setVisible(true);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.oprog) {
            Intent inte= new Intent(PovPrijava_Activity.this, Oprog_Activity.class);
            startActivity(inte);
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(PovPrijava_Activity.this, Lokacije_Glavna_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.pri || id == R.id.lok) {
            Intent inte= new Intent(PovPrijava_Activity.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.postavke) {
            Intent inte= new Intent(PovPrijava_Activity.this, Postavke_Activity.class);
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
                Intent inte = new Intent(PovPrijava_Activity.this, Login_Activity.class);
                startActivity(inte);
                finish();
            }
        }
        if (id == R.id.povijest1) {
        }
        return super.onOptionsItemSelected(item);
    }
    public String povijestPrijava(String s){
        tip = "dohvat_lista";
        Konektor konektor = new Konektor(this);
        konektor.execute(tip, s);
        return null;
    }
    public void obradaPodaci(String por) {
        String[] podaci = new String[0];
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(por);
            podaci = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                podaci[i] = jsonArray.getString(i);
                podaciArray.add(podaci[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void obradaSlike(String por) {
        String[] podaci = new String[0];
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(por);
            podaci = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                podaci[i] = jsonArray.getString(i);
                slikeArray.add(podaci[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void dohvatSlika(int poz){
        tip = "dohvat_slika";
        Konektor konektor = new Konektor(this);
        konektor.execute(tip, String.valueOf(poz));
    }
    public void postavljanje(){
        for(int i = 3; i < podaciArray.size(); i = i+11){
            odabraneStavke.add(podaciArray.get(i));
        }
        adapter.notifyDataSetChanged();
    }
    public void spremanjeSlika(){
        ((Varijable) this.getApplication()).setVar_slike(slikeArray);
        prikaz();
    }
    @Override
    public void onItemClick(View view, int position) {
        int pozicija = 0;
        if(position == 0){pozicija = 0;}
        if(position != 0 ){pozicija = position * 11;}
        odabranaPrijava.clear();
        for(int i = pozicija; i < (pozicija + 11); i++){
            odabranaPrijava.add(podaciArray.get(i));
        }
        ((Varijable) this.getApplication()).setVar_podaci(null);
        ((Varijable) this.getApplication()).setVar_podaci(odabranaPrijava);
        String test = podaciArray.get(pozicija+9);
        if(test.equals("da")){
            slikeArray.clear();
            dohvatSlika(Integer.parseInt(podaciArray.get(pozicija)));
            prikaz();
        }else{ prikaz(); }
    }
    public void prikaz(){
        Intent inte= new Intent(PovPrijava_Activity.this, PrijavaSpr_Activity.class);
        startActivity(inte);
    }
}