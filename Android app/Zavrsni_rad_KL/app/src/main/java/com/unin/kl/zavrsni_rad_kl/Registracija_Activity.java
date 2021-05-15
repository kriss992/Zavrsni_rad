package com.unin.kl.zavrsni_rad_kl;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
public class Registracija_Activity extends AppCompatActivity {
    EditText et_ime, et_prezime, et_oib, et_usr1, et_pas1, et_pas2;
    String ime = "";
    String prezime = "";
    String oib = "";
    String username = "";
    String password1 = "";
    String password2 = "";
    Button breg1;
    boolean dozvola = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracija);
        getSupportActionBar().setTitle("Registracija");
        et_ime = (EditText) findViewById(R.id.et_ime);
        et_prezime = (EditText) findViewById(R.id.et_prezime);
        et_oib = (EditText) findViewById(R.id.et_oib);
        et_usr1 = (EditText) findViewById(R.id.et_user1);
        et_pas1 = (EditText) findViewById(R.id.et_pass1);
        et_pas2 = (EditText) findViewById(R.id.et_pass2);
        breg1 = findViewById(R.id.btn_reg1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_glavna__login, menu);
        MenuItem odjava = menu.findItem(R.id.odjava1);
        MenuItem povijest = menu.findItem(R.id.povijest1);
        odjava.setVisible(false);
        povijest.setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.oprog) {
            Intent inte= new Intent(Registracija_Activity.this, Oprog_Activity.class);
            startActivity(inte);
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(Registracija_Activity.this, Lokacije_Glavna_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.pri || id == R.id.pri2) {
            Intent inte= new Intent(Registracija_Activity.this, Login_Activity.class);
            startActivity(inte);
            finish();
        }
        if (id == R.id.postavke) {
            Intent inte= new Intent(Registracija_Activity.this, Postavke_Activity.class);
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
                Intent inte = new Intent(Registracija_Activity.this, Login_Activity.class);
                startActivity(inte);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void dozvolaKlik(View v){
        dozvola = ((CheckBox) v).isChecked();
    }
    public  void klikReg1(View v){
        password1 = et_pas1.getText().toString();
        password2 = et_pas2.getText().toString();
        if((password1).equals(password2) && dozvola == true) {
            ime = et_ime.getText().toString();
            prezime = et_prezime.getText().toString();
            oib = et_oib.getText().toString();
            username = et_usr1.getText().toString();
            String tip = "reg";
            Konektor konektor = new Konektor(this);
            konektor.execute(tip, ime, prezime, oib, username, password1);
        }else{
            et_pas1.setText("");
            et_pas2.setText("");
            Toast.makeText(this, "Obrazac registracije nije pravilno ispunjen!", Toast.LENGTH_SHORT).show();
        }
    }
    public void registrirano(){
        Intent inte = new Intent(Registracija_Activity.this, Login_Activity.class);
        startActivity(inte);
        finish();
    }
}