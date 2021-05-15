package com.unin.kl.zavrsni_rad_kl;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class Login_Activity extends AppCompatActivity {
    EditText et_pas, et_usr;
    String username, password;
    Button breg, bpri, banon, bpov;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Prijava u sustav");
        et_pas = (EditText) findViewById(R.id.et_pass);
        et_usr = (EditText) findViewById(R.id.et_user);
        bpri = findViewById(R.id.btn_prijava);
        breg = findViewById(R.id.btn_reg);
        banon = findViewById(R.id.btn_anon);
        bpov = findViewById(R.id.btn_pov);
        prijavljeno_set();
    }
    public void klikLogin(View v){
        if(((Varijable) this.getApplication()).getVar_log().equals("ne")){
            prijavljeno_set();
            prijava();
        }if(((Varijable) this.getApplication()).getVar_log().equals("da")){
            username = et_usr.getText().toString();
            password = et_pas.getText().toString();
            String tip = "login";
            Konektor konektor = new Konektor(this);
            konektor.execute(tip, username, password);
        }
    }
    public void klikAnon(View v){
        ((Varijable) this.getApplicationContext()).setVar_anon("da");
        Intent inte= new Intent(Login_Activity.this, Prijava_Activity.class);
        startActivity(inte);
    }
    public void klikReg(View v){
        Intent inte = new Intent(Login_Activity.this, Registracija_Activity.class);
        startActivity(inte);
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
            Intent inte= new Intent(Login_Activity.this, Oprog_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(Login_Activity.this, Lokacije_Glavna_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.postavke) {
            Intent inte= new Intent(Login_Activity.this, Postavke_Activity.class);
            startActivity(inte);
            finish();
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
                Intent inte = new Intent(Login_Activity.this, Login_Activity.class);
                startActivity(inte);
                finish();
            }
        }
        if(id == R.id.povijest1){
            pov();
        }
        return super.onOptionsItemSelected(item);
    }
    public void prijava(){
        Intent inte = new Intent(Login_Activity.this, Prijava_Activity.class);
        startActivity(inte);
    }
    public void prijavljeno_set(){
        if(((Varijable) this.getApplication()).getVar_log().equals("ne")){
            invalidateOptionsMenu();
            breg.setVisibility(View.INVISIBLE);
            et_pas.setVisibility(View.INVISIBLE);
            et_usr.setVisibility(View.INVISIBLE);
            bpov.setVisibility(View.VISIBLE);
        }
        else{
            bpov.setVisibility(View.INVISIBLE);
        }
    }
    public void klikPov(View v){ pov(); }
    public void pov(){
        Intent inte= new Intent(Login_Activity.this, PovPrijava_Activity.class);
        startActivity(inte);
    }
    @Override
    public void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
}