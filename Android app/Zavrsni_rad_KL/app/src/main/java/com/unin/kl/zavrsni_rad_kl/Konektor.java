package com.unin.kl.zavrsni_rad_kl;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import static android.content.Context.MODE_PRIVATE;
public class Konektor extends AsyncTask<String, Integer, String> {
    Context kontekst;
    String url_str = "";
    String slika_str;
    String rezultat = "";
    String rezultat_s = "";
    String linija = "";
    String ip = "";
    private Bitmap bitmap;
    Uri uri;
    String tip = "";
    String korisnicko_ime, lozinka;
    Konektor (Context ctx) { kontekst = ctx; }
    SharedPreferences sp;
    HttpURLConnection httpURLKonekcija;
    OutputStream izlazniStream;
    BufferedWriter bufferPisac;
    String post_data;
    InputStream ulazniStream;
    BufferedReader bufferCitac;
    URL url;
    @Override
    protected String doInBackground(String... parametar) {
        sp = kontekst.getSharedPreferences("SPREMANJE_POSTAVKI", MODE_PRIVATE);
        ip=sp.getString("IP_server", "127.0.0.1");
        tip = parametar[0];
        if(tip.equals("login")) {
            url_str = "http://"+ip+"/zavrsnirad/login.php";
            try {
                korisnicko_ime = parametar[1];
                lozinka = parametar[2];
                url = new URL(url_str);
                httpURLKonekcija = (HttpURLConnection)url.openConnection();
                httpURLKonekcija.setRequestMethod("POST");
                httpURLKonekcija.setDoOutput(true);
                httpURLKonekcija.setDoInput(true);
                izlazniStream = httpURLKonekcija.getOutputStream();
                bufferPisac = new BufferedWriter(new OutputStreamWriter(izlazniStream,
                        "UTF-8"));
                post_data = URLEncoder.encode("korisnicko_ime","UTF-8")+
                        "="+URLEncoder.encode(korisnicko_ime,"UTF-8")+
                        "&"+URLEncoder.encode("lozinka","UTF-8")+
                        "="+ URLEncoder.encode(lozinka,"UTF-8");
                bufferPisac.write(post_data);
                bufferPisac.flush();
                bufferPisac.close();
                izlazniStream.close();
                ulazniStream = httpURLKonekcija.getInputStream();
                bufferCitac = new BufferedReader(new InputStreamReader(ulazniStream,
                        "UTF-8"));
                while((linija = bufferCitac.readLine())!= null) {
                    rezultat += linija;
                }
                bufferCitac.close();
                ulazniStream.close();
                httpURLKonekcija.disconnect();
                return rezultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(tip.equals("reg")) {
            url_str = "http://"+ip+"/zavrsnirad/registracija.php";
            String ime = parametar[1];
            String prezime = parametar[2];
            String oib = parametar[3];
            korisnicko_ime = parametar[4];
            lozinka = parametar[5];
            try {
                url = new URL(url_str);
                httpURLKonekcija = (HttpURLConnection)url.openConnection();
                httpURLKonekcija.setRequestMethod("POST");
                httpURLKonekcija.setDoOutput(true);
                httpURLKonekcija.setDoInput(true);
                izlazniStream = httpURLKonekcija.getOutputStream();
                bufferPisac = new BufferedWriter(new OutputStreamWriter(izlazniStream,
                        "UTF-8"));
                post_data = URLEncoder.encode("ime","UTF-8")+"="
                        +URLEncoder.encode(ime,"UTF-8")+
                        "&"+URLEncoder.encode("prezime","UTF-8")+"="
                        + URLEncoder.encode(prezime,"UTF-8")+
                        "&"+URLEncoder.encode("oib","UTF-8")+"="
                        + URLEncoder.encode(oib,"UTF-8")+
                        "&"+URLEncoder.encode("korisnicko_ime","UTF-8")+"="
                        + URLEncoder.encode(korisnicko_ime,"UTF-8")+
                        "&"+URLEncoder.encode("lozinka","UTF-8")+"="
                        + URLEncoder.encode(lozinka,"UTF-8");
                bufferPisac.write(post_data);
                bufferPisac.flush();
                bufferPisac.close();
                izlazniStream.close();
                ulazniStream = httpURLKonekcija.getInputStream();
                bufferCitac = new BufferedReader(new InputStreamReader(ulazniStream,
                        "UTF-8"));
                rezultat = "";
                linija = "";
                while((linija = bufferCitac.readLine())!= null) {
                    rezultat += linija;
                }
                bufferCitac.close();
                ulazniStream.close();
                httpURLKonekcija.disconnect();
                return rezultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(tip.equals("promjena")) {
            url_str = "http://"+ip+"/zavrsnirad/promjena.php";
            String id = parametar[1];
            String ime = parametar[2];
            String prezime = parametar[3];
            String oib = parametar[4];
            korisnicko_ime = parametar[5];
            lozinka = parametar[6];
            try {
                url = new URL(url_str);
                httpURLKonekcija = (HttpURLConnection)url.openConnection();
                httpURLKonekcija.setRequestMethod("POST");
                httpURLKonekcija.setDoOutput(true);
                httpURLKonekcija.setDoInput(true);
                izlazniStream = httpURLKonekcija.getOutputStream();
                bufferPisac = new BufferedWriter(new OutputStreamWriter(izlazniStream,
                        "UTF-8"));
                post_data = URLEncoder.encode("tip","UTF-8")+
                        "="+URLEncoder.encode(tip,"UTF-8")+
                        "&"+URLEncoder.encode("id","UTF-8")+
                        "="+ URLEncoder.encode(id,"UTF-8")+
                        "&"+URLEncoder.encode("ime","UTF-8")+
                        "="+URLEncoder.encode(ime,"UTF-8")+
                        "&"+URLEncoder.encode("prezime","UTF-8")+
                        "="+ URLEncoder.encode(prezime,"UTF-8")+
                        "&"+URLEncoder.encode("oib","UTF-8")+
                        "="+ URLEncoder.encode(oib,"UTF-8")+
                        "&"+URLEncoder.encode("korisnicko_ime","UTF-8")+
                        "="+ URLEncoder.encode(korisnicko_ime,"UTF-8")+
                        "&"+URLEncoder.encode("lozinka","UTF-8")+
                        "="+ URLEncoder.encode(lozinka,"UTF-8");
                bufferPisac.write(post_data);
                bufferPisac.flush();
                bufferPisac.close();
                izlazniStream.close();
                ulazniStream = httpURLKonekcija.getInputStream();
                bufferCitac = new BufferedReader(new InputStreamReader(ulazniStream,
                        "UTF-8"));
                rezultat = "";
                linija = "";
                while((linija = bufferCitac.readLine())!= null) {
                    rezultat += linija;
                }
                bufferCitac.close();
                ulazniStream.close();
                httpURLKonekcija.disconnect();
                return rezultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(tip.equals("brisanje")) {
            url_str = "http://"+ip+"/zavrsnirad/promjena.php";
            String id = parametar[1];
            try {
                url = new URL(url_str);
                httpURLKonekcija = (HttpURLConnection)url.openConnection();
                httpURLKonekcija.setRequestMethod("POST");
                httpURLKonekcija.setDoOutput(true);
                httpURLKonekcija.setDoInput(true);
                izlazniStream = httpURLKonekcija.getOutputStream();
                bufferPisac = new BufferedWriter(new OutputStreamWriter(izlazniStream,
                        "UTF-8"));
                post_data = URLEncoder.encode("tip","UTF-8")+
                        "="+URLEncoder.encode(tip,"UTF-8")+
                        "&"+URLEncoder.encode("id","UTF-8")+
                        "="+URLEncoder.encode(id,"UTF-8");
                bufferPisac.write(post_data);
                bufferPisac.flush();
                bufferPisac.close();
                izlazniStream.close();
                ulazniStream = httpURLKonekcija.getInputStream();
                bufferCitac = new BufferedReader(new InputStreamReader(ulazniStream,"UTF-8"));
                rezultat = "";
                linija = "";
                while((linija = bufferCitac.readLine())!= null) {
                    rezultat += linija;
                }
                bufferCitac.close();
                ulazniStream.close();
                httpURLKonekcija.disconnect();
                return rezultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(tip.equals("prijava")) {
            url_str = "http://"+ip+"/zavrsnirad/prijava.php";
            String status = parametar[1];
            String korisnik_id = parametar[2];
            String lat = parametar[3];
            String lon = parametar[4];
            String adresa = parametar[5];
            String zupanija = parametar[6];
            String poruka = parametar[7];
            String slike = parametar[8];
            String slika_p = parametar[9];
            String datum = parametar[10];
            try {
                url = new URL(url_str);
                httpURLKonekcija = (HttpURLConnection)url.openConnection();
                httpURLKonekcija.setRequestMethod("POST");
                httpURLKonekcija.setDoOutput(true);
                httpURLKonekcija.setDoInput(true);
                izlazniStream = httpURLKonekcija.getOutputStream();
                bufferPisac = new BufferedWriter(new OutputStreamWriter(izlazniStream,
                        "UTF-8"));
                post_data = URLEncoder.encode("tip","UTF-8")+
                        "="+URLEncoder.encode(tip,"UTF-8")+
                        "&"+URLEncoder.encode("anon","UTF-8")+
                        "="+URLEncoder.encode(status,"UTF-8")+
                        "&"+URLEncoder.encode("korisnik_id","UTF-8")+
                        "="+URLEncoder.encode(korisnik_id,"UTF-8")+
                        "&"+URLEncoder.encode("latituda","UTF-8")+
                        "="+ URLEncoder.encode(lat,"UTF-8")+
                        "&"+URLEncoder.encode("longituda","UTF-8")+
                        "="+ URLEncoder.encode(lon,"UTF-8")+
                        "&"+URLEncoder.encode("adresa","UTF-8")+
                        "="+ URLEncoder.encode(adresa,"UTF-8")+
                        "&"+URLEncoder.encode("zupanija","UTF-8")+
                        "="+ URLEncoder.encode(zupanija,"UTF-8")+
                        "&"+URLEncoder.encode("poruka","UTF-8")+
                        "="+ URLEncoder.encode(poruka,"UTF-8")+
                        "&"+URLEncoder.encode("ima_slika","UTF-8")+
                        "="+ URLEncoder.encode(slika_p,"UTF-8")+
                        "&"+URLEncoder.encode("datum","UTF-8")+
                        "="+ URLEncoder.encode(datum,"UTF-8");
                bufferPisac.write(post_data);
                bufferPisac.flush();
                bufferPisac.close();
                izlazniStream.close();
                ulazniStream = httpURLKonekcija.getInputStream();
                bufferCitac = new BufferedReader(new InputStreamReader(ulazniStream,
                        "UTF-8"));
                rezultat = "";
                linija = "";
                while((linija = bufferCitac.readLine())!= null) {
                    rezultat += linija;
                }
                bufferCitac.close();
                ulazniStream.close();
                httpURLKonekcija.disconnect();
                String[] podaci = new String[0];
                String split[] = rezultat.split("//");
                if (split[0].equals("uspjesno_prijava")) {
                    JSONArray jsonArray = null;
                    try {
                        jsonArray = new JSONArray(split[1]);
                        podaci = new String[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); i++) {
                            podaci[i] = jsonArray.getString(i);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (slika_p.equals("da")){
                        String bezZagada = slike.substring( 1, slike.length() - 1);
                        String[] lista = bezZagada.split( ", ");
                        for (int i = 0; i < lista.length; i++) {
                            url_str = "http://" + ip + "/zavrsnirad/slike.php";
                            String st = "Šaljem "+(i+1)+"/"+lista.length+" slika.";
                            ((Prijava_Activity) kontekst).slanje(st);
                            try {
                                uri = Uri.parse(lista[i]);
                                bitmap = BitmapFactory.decodeFile(uri.getPath());
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap = MediaStore.Images.Media.getBitmap(
                                        kontekst.getContentResolver(),Uri.parse(lista[i]));
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                bitmap.recycle();
                                byte[] array = stream.toByteArray();
                                slika_str = Base64.encodeToString(array, 0);
                                url = new URL(url_str);
                                httpURLKonekcija = (HttpURLConnection) url.openConnection();
                                httpURLKonekcija.setRequestMethod("POST");
                                httpURLKonekcija.setDoOutput(true);
                                httpURLKonekcija.setDoInput(true);
                                izlazniStream = httpURLKonekcija.getOutputStream();
                                bufferPisac = new BufferedWriter(new OutputStreamWriter(
                                        izlazniStream, "UTF-8"));
                                post_data = URLEncoder.encode("slika_str", "UTF-8") +
                                        "=" + URLEncoder.encode(slika_str, "UTF-8") +
                                        "&" + URLEncoder.encode("ime", "UTF-8") +
                                        "=" + URLEncoder.encode("Slika", "UTF-8") +
                                        "&" + URLEncoder.encode("id_prijenos", "UTF-8") +
                                        "=" + URLEncoder.encode(podaci[0], "UTF-8");
                                bufferPisac.write(post_data);
                                bufferPisac.flush();
                                bufferPisac.close();
                                izlazniStream.close();
                                Log.i("MOJ","Slika_str poslano: "+slika_str);
                                ulazniStream = httpURLKonekcija.getInputStream();
                                bufferCitac = new BufferedReader(new InputStreamReader(ulazniStream,
                                        "UTF-8"));
                                rezultat_s = "";
                                linija = "";
                                while ((linija = bufferCitac.readLine()) != null) {
                                    rezultat_s += linija;
                                }
                                if (rezultat_s.equals("neuspjesno_slika")) {
                                    return rezultat_s;
                                }
                                bufferCitac.close();
                                ulazniStream.close();
                                httpURLKonekcija.disconnect();
                            } catch (MalformedURLException e) {
                                    e.printStackTrace();
                            } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        }
                    }
                }
                return rezultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(tip.equals("dohvat_lista")) {
            url_str = "http://"+ip+"/zavrsnirad/dohvat.php";
            String id = parametar[1];
            try {
                url = new URL(url_str);
                httpURLKonekcija = (HttpURLConnection)url.openConnection();
                httpURLKonekcija.setRequestMethod("POST");
                httpURLKonekcija.setDoOutput(true);
                httpURLKonekcija.setDoInput(true);
                izlazniStream = httpURLKonekcija.getOutputStream();
                bufferPisac = new BufferedWriter(new OutputStreamWriter(izlazniStream, "UTF-8"));
                post_data = URLEncoder.encode("tip","UTF-8")+"="+URLEncoder.encode(tip,"UTF-8")+
                        "&"+URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                bufferPisac.write(post_data);
                bufferPisac.flush();
                bufferPisac.close();
                izlazniStream.close();
                ulazniStream = httpURLKonekcija.getInputStream();
                bufferCitac = new BufferedReader(new InputStreamReader(ulazniStream,"UTF-8"));
                rezultat = "";
                linija = "";
                while((linija = bufferCitac.readLine())!= null) {
                    rezultat += linija;
                }
                bufferCitac.close();
                ulazniStream.close();
                httpURLKonekcija.disconnect();
                return rezultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(tip.equals("dohvat_slika")) {
            url_str = "http://"+ip+"/zavrsnirad/dohvat.php";
            String id = parametar[1];
            try {
                url = new URL(url_str);
                httpURLKonekcija = (HttpURLConnection)url.openConnection();
                httpURLKonekcija.setRequestMethod("POST");
                httpURLKonekcija.setDoOutput(true);
                httpURLKonekcija.setDoInput(true);
                izlazniStream = httpURLKonekcija.getOutputStream();
                bufferPisac = new BufferedWriter(new OutputStreamWriter(izlazniStream, "UTF-8"));
                post_data = URLEncoder.encode("tip","UTF-8")+"="+URLEncoder.encode(tip,"UTF-8")+
                        "&"+URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                bufferPisac.write(post_data);
                bufferPisac.flush();
                bufferPisac.close();
                izlazniStream.close();
                ulazniStream = httpURLKonekcija.getInputStream();
                bufferCitac = new BufferedReader(new InputStreamReader(ulazniStream,"UTF-8"));
                rezultat = "";
                linija = "";
                while((linija = bufferCitac.readLine())!= null) {
                    rezultat += linija;
                }
                bufferCitac.close();
                ulazniStream.close();
                httpURLKonekcija.disconnect();
                return rezultat;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onPreExecute() {    }
    @Override
    protected void onPostExecute(String rezultat) {
        if(tip.equals("login")) {
            String[] podaci = new String[0];
            String split[] = rezultat.split("//");
            if (split[0].equals("uspjesno_login")) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(split[1]);
                    podaci = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        podaci[i] = jsonArray.getString(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ((Varijable) kontekst.getApplicationContext()).setVar_user(Integer.parseInt
                        (podaci[0]));
                ((Varijable) kontekst.getApplicationContext()).setVar_oib(podaci[1]);
                ((Varijable) kontekst.getApplicationContext()).setVar_ime(podaci[2]);
                ((Varijable) kontekst.getApplicationContext()).setVar_prezime(podaci[3]);
                ((Varijable) kontekst.getApplicationContext()).setVar_korisnicko_ime(podaci[4]);
                ((Varijable) kontekst.getApplicationContext()).setVar_lozinka(podaci[5]);
                ((Varijable) kontekst.getApplicationContext()).setVar_log("ne");
                Toast.makeText(kontekst, "Prijavljeni ste!", Toast.LENGTH_LONG).show();
                ((Login_Activity) kontekst).prijavljeno_set();
            }
            if (rezultat.equals("neuspjesno_login")){
                Toast.makeText(kontekst, "Prijava nije uspjela!", Toast.LENGTH_LONG).show();
            }
        }
        if(tip.equals("reg")){
            if (rezultat.equals("uspjesno_reg")) {
                Toast.makeText(kontekst, "Registracija je uspjela, možete se prijaviti.",
                        Toast.LENGTH_LONG).show();
                ((Registracija_Activity) kontekst).registrirano();
            }
            if (rezultat.equals("neuspjesno_reg")){
                Toast.makeText(kontekst, "Registracija nije uspjela!", Toast.LENGTH_LONG)
            .show();
            }
        }
        if(tip.equals("promjena")) {
            String[] podaci = new String[0];
            String split[] = rezultat.split("//");
            if (split[0].equals("uspjesno_promjena")) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(split[1]);
                    podaci = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        podaci[i] = jsonArray.getString(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ((Varijable) kontekst.getApplicationContext()).setVar_user(Integer.parseInt
                        (podaci[0]));
                ((Varijable) kontekst.getApplicationContext()).setVar_oib(podaci[1]);
                ((Varijable) kontekst.getApplicationContext()).setVar_ime(podaci[2]);
                ((Varijable) kontekst.getApplicationContext()).setVar_prezime(podaci[3]);
                ((Varijable) kontekst.getApplicationContext()).setVar_korisnicko_ime(podaci[4]);
                ((Varijable) kontekst.getApplicationContext()).setVar_lozinka(podaci[5]);
                ((Varijable) kontekst.getApplicationContext()).setVar_log("ne");
                Toast.makeText(kontekst, "Uspješno ste promjenili profil.", Toast.LENGTH_LONG)
                        .show();
            }
            if (rezultat.equals("neuspjesno_promjena")){
                Toast.makeText(kontekst, "Izmjena profila nije uspjela!", Toast.LENGTH_LONG)
                        .show();
            }
        }
        if(tip.equals("brisanje")){
            if (rezultat.equals("uspjesno_brisanje")) {
                Toast.makeText(kontekst, "Uspješno ste izbrisali profil.", Toast.LENGTH_LONG)
                        .show();
                ((Postavke_Activity) kontekst).odjava();
            }
            if (rezultat.equals("neuspjesno_brisanje")){
                Toast.makeText(kontekst, "Brisanje profila nije uspjelo!", Toast.LENGTH_LONG).show();
            }
        }
        if(tip.equals("prijava")){
            String split[] = rezultat.split("//");
            if (split[0].equals("uspjesno_prijava")) {
                if((((Varijable) kontekst.getApplicationContext()).getVar_anon()).equals("da")){
                    ((Varijable) kontekst.getApplicationContext()).setVar_anon("ne");
                }
                Toast.makeText(kontekst, "Prijava je uspjela!", Toast.LENGTH_LONG).show();
                ((Prijava_Activity) kontekst).poslano();
            }
            if (rezultat.equals("neuspjesno_prijava")){
                Toast.makeText(kontekst, "Prijava nije uspjela!", Toast.LENGTH_LONG).show();
            }
            if(rezultat.equals("neuspjesno_slika")){
                Toast.makeText(kontekst, "Prijava uspjela uspjela, ne možemo poslati slike!", Toast.LENGTH_LONG).show();
            }
        }
        if(tip.equals("dohvat_lista")){
            String[] podaci1 = new String[0];
            String split[] = rezultat.split("//");
            if (split[0].equals("uspjesno_dohvat_lista")) {
                JSONArray jsonArray = null;
                Toast.makeText(kontekst, "Dohvaćena lista prijava.", Toast.LENGTH_LONG).show();
                try {
                    jsonArray = new JSONArray(split[1]);
                    podaci1 = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        podaci1[i] = jsonArray.getString(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i < podaci1.length; i++){
                    ((PovPrijava_Activity) kontekst).obradaPodaci(podaci1[i]);
                    if((podaci1.length-1)==i){
                        ((PovPrijava_Activity) kontekst).postavljanje();
                    }
                }
            }
            if (split[0].equals("neuspjesno_dohvat_lista")){
                Toast.makeText(kontekst, "Neuspješno dohvaćena lista prijava!!!", Toast.LENGTH_LONG).show();
            }
        }
        if(tip.equals("dohvat_slika")){
            String[] podaci1 = new String[0];
            String split[] = rezultat.split("//");
            if (split[0].equals("uspjesno_dohvat_slika")) {
                JSONArray jsonArray = null;
                Toast.makeText(kontekst, "Dohvaćene slike prijave.", Toast.LENGTH_LONG).show();
                try {
                    jsonArray = new JSONArray(split[1]);
                    podaci1 = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        podaci1[i] = jsonArray.getString(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for(int i = 0; i < podaci1.length; i++){
                    ((PovPrijava_Activity) kontekst).obradaSlike(podaci1[i]);
                    if((podaci1.length-1)==i){
                        ((PovPrijava_Activity) kontekst).spremanjeSlika();
                    }
                }
            }
            if (split[0].equals("neuspjesno_dohvat_slika")){
                Toast.makeText(kontekst, "Neuspješno dohvaćene slike prijave!!!", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onProgressUpdate(Integer... values) { super.onProgressUpdate(values); }
}