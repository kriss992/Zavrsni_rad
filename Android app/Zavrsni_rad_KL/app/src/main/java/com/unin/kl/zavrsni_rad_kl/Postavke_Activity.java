package com.unin.kl.zavrsni_rad_kl;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
public class Postavke_Activity extends AppCompatActivity {
    int user_id;
    String p_ip = "";
    EditText et_ime, et_prezime, et_oib, et_korisnik, et_pass1, et_pass2, ip;
    TextView tv_naslov;
    ImageButton btnPostavi;
    Button btnPromjeni, btnIzbrisi;
    String ime, prezime, oib, korisnik, pass1, pass2, tip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postavke);
        getSupportActionBar().setTitle("Postavke");
        btnPostavi = findViewById(R.id.btn_postavi);
        btnPromjeni = findViewById(R.id.btn_promjene);
        btnIzbrisi = findViewById(R.id.btn_izbrisi);
        tv_naslov = findViewById(R.id.tv_naslov);
        et_ime = findViewById(R.id.et_ime);
        et_prezime = findViewById(R.id.et_prezime);
        et_oib = findViewById(R.id.et_oib);
        et_korisnik = findViewById(R.id.et_user1);
        et_pass1 = findViewById(R.id.et_pass1);
        et_pass2 = findViewById(R.id.et_pass2);
        user_id();
        et_ime.setText(((Varijable) this.getApplicationContext()).getVar_ime());
        et_prezime.setText(((Varijable) this.getApplicationContext()).getVar_prezime());
        et_oib.setText(((Varijable) this.getApplicationContext()).getVar_oib());
        et_korisnik.setText(((Varijable) this.getApplicationContext()).getVar_korisnicko_ime());
        et_pass1.setText(((Varijable) this.getApplicationContext()).getVar_lozinka());
        et_pass2.setText(((Varijable) this.getApplicationContext()).getVar_lozinka());
        if(((Varijable) this.getApplicationContext()).getVar_log().equals("da")) {
            btnPromjeni.setVisibility(View.INVISIBLE);
            btnIzbrisi.setVisibility(View.INVISIBLE);
            tv_naslov.setVisibility(View.INVISIBLE);
            et_ime.setVisibility(View.INVISIBLE);
            et_prezime.setVisibility(View.INVISIBLE);
            et_oib.setVisibility(View.INVISIBLE);
            et_korisnik.setVisibility(View.INVISIBLE);
            et_pass1.setVisibility(View.INVISIBLE);
            et_pass2.setVisibility(View.INVISIBLE);
        }
        ip = findViewById(R.id.et_ip);
        SharedPreferences sp = getSharedPreferences("SPREMANJE_POSTAVKI", MODE_PRIVATE);
        p_ip=sp.getString("IP_server", "127.0.0.1");
        ip.setText(p_ip);
        btnPostavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ip_add = ip.getText().toString();
                SharedPreferences.Editor sped = sp.edit();
                sped.putString("IP_server", ip_add);
                sped.commit();
                Toast.makeText(Postavke_Activity.this, "Postavili ste IP adesu " +
                        "servera na: "+ip_add, Toast.LENGTH_SHORT).show();
            }
        });
        btnPromjeni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promjena();
            }
        });
        btnIzbrisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brisanje();
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
            Intent inte= new Intent(Postavke_Activity.this, Oprog_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(Postavke_Activity.this, Lokacije_Glavna_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.pri2 || id == R.id.pri) {
            Intent inte= new Intent(Postavke_Activity.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.odjava1) {
            odjava();
        }
        if(id == R.id.povijest1){
            Intent inte= new Intent(Postavke_Activity.this, PovPrijava_Activity.class);
            startActivity(inte);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
    public void user_id(){
        user_id = ((Varijable) this.getApplicationContext()).getVar_user();
    }
    public void brisanje() {
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
    public void klikNe(){
    }
    public void klikDa(){
        tip = "brisanje";
        user_id();
        Konektor konektor = new Konektor(this);
        konektor.execute(tip, String.valueOf(user_id));
    }
    public void odjava(){
        if(((Varijable) this.getApplication()).getVar_log().equals("ne")) {
            ((Varijable) this.getApplication()).setVar_log("da");
            ((Varijable) this.getApplicationContext()).setVar_user(0);
            ((Varijable) this.getApplicationContext()).setVar_ime("");
            ((Varijable) this.getApplicationContext()).setVar_prezime("");
            ((Varijable) this.getApplicationContext()).setVar_oib("");
            ((Varijable) this.getApplicationContext()).setVar_korisnicko_ime("");
            ((Varijable) this.getApplicationContext()).setVar_lozinka("");
            Intent inte = new Intent(Postavke_Activity.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
    }
    public void promjena(){
        tip = "promjena";
        user_id();
        ime = et_ime.getText().toString();
        prezime = et_prezime.getText().toString();
        oib = et_oib.getText().toString();
        korisnik = et_korisnik.getText().toString();
        pass1 = et_pass1.getText().toString();
        pass2 = et_pass2.getText().toString();
        if(pass1.equals(pass2)) {
            Konektor konektor = new Konektor(this);
            konektor.execute(tip, String.valueOf(user_id), ime, prezime, oib, korisnik, pass1);
        }else{
            Toast.makeText(Postavke_Activity.this, "Lozinke se ne podudaraju!", Toast.LENGTH_SHORT).show();
        }
    }
}