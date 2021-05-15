package com.unin.kl.zavrsni_rad_kl;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
public class Oprog_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oprog_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        WebView wv = (WebView) findViewById(R.id.webView);
        wv.setWebViewClient(new WebViewClient());
        wv.setWebChromeClient(new WebChromeClient());
        String html;
        html="\n" +
                "<html>\n" +
                "<body bgcolor=\"#f4efea\">\n" +
                "<p style=\"text-align: center; font-size: 20px;\"><strong>Sveučili&scaron;te " +
                "sjever</strong></p>\n<p style=\"font-family: Georgia, serif;\"><strong>Odjel:" +
                "</strong> Elektrotehnika</p>\n<p style=\"font-family: Georgia, serif;\">" +
                "<strong>Broj zavr&scaron;nog rada:</strong> 446/EL/2019</p>\n<p style=\"" +
                "font-family: Georgia, serif;\"><strong>Naslov zavr&scaron;nog rada:</strong>" +
                "</p>\n<p style=\"font-family: Georgia, serif;\"><em>Izvedba Android mobilne " +
                "aplikacije&nbsp;s primjenom lokacijskih servisa</em></p>\n" +
                "<p style=\"font-family: Georgia, serif;\"><strong>Student:&nbsp;</strong>" +
                "Kristijan Lukaček, 5043/601</p>\n<p style=\"font-family: Georgia, serif;\">" +
                "<strong>Mentor:&nbsp;</strong>mr.sc. Matija Mikac</p>\n<p style=\"font-family: " +
                "Georgia, serif;\"><strong>Datum izrade</strong>: 15. rujan 2019.</p>\n" +
                "<p style=\"font-family: Georgia, serif;\"><strong>Zadatak zavr&scaron;nog rada:" +
                "</strong></p>\n<p style=\"font-family: Georgia, serif;\">Cilj zavr&scaron;nog " +
                "rada je izvesti potpuno funkcionalnu mobilnu aplikaciju za Android operacijski" +
                " sustav bazirani na pro&scaron;irenoj primjeni lokacijskih servisa (pohrana GPS" +
                " lokacija, vizualizacija). Potrebno je teoretski obraditi elemente Android" +
                " operacijskog sustava i razvojnog okruženja vezane uz samu izvedbu aplikacije." +
                " Također je nužno implementirati osnovne elemente web aplikacije koja će " +
                "preuzimati podatke sa uređaja i omogućiti određenu razinu vizualizacije i u " +
                "obliku web aplikacije - pri tome voditi računa o identifikaciji korisnika. " +
                "Kao dodatne (pro&scaron;irena primjena) mogućnosti implementirati " +
                "funkcionalnost slanja proizvoljnih podataka i fotografija sa geo-pozicijom i" +
                " dohvat povratnog statusa sa poslužitelja (na primjer, prijave komunalnih" +
                " problema redarima i slično)<br /> <br />U radu je potrebno: <br />&bull; " +
                "obraditi i opisati ključne kori&scaron;tene elemente Android operacijskog " +
                "sustava - lokacijske servise, mogućnosti pohrane podataka na uređaju, mogućnosti "+
                "mrežne komunikacije i slanja podataka na poslužitelj, pristup vizualizaciji" +
                " lokacijskih podataka u obliku mapa, kontrola kamere itd.<br />&bull; " +
                "implementirati i dokumentirati aplikaciju koja će omogućiti GPS praćenje<br />" +
                "&bull; dodatno implementirati mogućnost slanja podataka i fotografija sa uređaja "+
                "na server<br />&bull; implementirati osnovne elemente web aplikacije nužne za" +
                " funkcioniranje aplikacije ovog tipa</p></body>\n</html>\n";
        wv.loadData(html, "text/html", "UTF-8");
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
        }
        if (id == R.id.lok || id == R.id.lok2) {
            Intent inte= new Intent(Oprog_Activity.this, Lokacije_Glavna_Activity.class);
            inte.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(inte);
            finish();
        }
        if (id == R.id.pri || id == R.id.pri2) {
            Intent inte= new Intent(Oprog_Activity.this, Prijava_Activity.class);
            startActivity(inte);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}