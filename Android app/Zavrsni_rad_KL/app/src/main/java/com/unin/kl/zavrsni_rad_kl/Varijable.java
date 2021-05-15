package com.unin.kl.zavrsni_rad_kl;
import android.app.Application;
import java.util.ArrayList;
public class Varijable extends Application {
    public static String log_stat = "da";
    public static int user_id = 0;
    public static String anonimna = "ne";
    public static String ime = "";
    public static String prezime = "";
    public static String oib = "";
    public static String korisnicko_ime = "";
    public static String lozinka = "";
    public static String pregledPri = "ne";
    ArrayList<String > podaciArray = new ArrayList<>();
    ArrayList<String > slikeArray = new ArrayList<>();
    public int getVar_user() { return user_id; }
    public void setVar_user(int sVar_user) { this.user_id = sVar_user; }
    public String getVar_log() { return log_stat; }
    public void setVar_log(String sVar_log) { this.log_stat = sVar_log; }
    public String getVar_anon() { return anonimna; }
    public void setVar_anon(String sVar_anon) { this.anonimna = sVar_anon; }
    public String getVar_ime() { return ime; }
    public void setVar_ime(String sVar_ime) { this.ime = sVar_ime; }
    public String getVar_prezime() { return prezime; }
    public void setVar_prezime(String sVar_prezime) { this.prezime = sVar_prezime; }
    public String getVar_oib() { return oib; }
    public void setVar_oib(String sVar_oib) { this.oib = sVar_oib; }
    public String getVar_korisnicko_ime() { return korisnicko_ime; }
    public void setVar_korisnicko_ime(String sVar_korisnicko_ime) { this.korisnicko_ime = sVar_korisnicko_ime; }
    public String getVar_lozinka() { return lozinka; }
    public void setVar_lozinka(String sVar_lozinka) { this.lozinka = sVar_lozinka; }
    public ArrayList getVar_podaci() { return podaciArray; }
    public void setVar_podaci(ArrayList sVar_podaci) { this.podaciArray = sVar_podaci; }
    public ArrayList getVar_slike() { return slikeArray; }
    public void setVar_slike(ArrayList sVar_slike) { this.slikeArray = sVar_slike; }
}