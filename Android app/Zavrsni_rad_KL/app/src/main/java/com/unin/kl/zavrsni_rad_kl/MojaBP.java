package com.unin.kl.zavrsni_rad_kl;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class MojaBP extends android.database.sqlite.SQLiteOpenHelper {
    private static final String DATABSE_IME_LOK = "lokacija_bp.db";
    private static final String TABLICA_IME_LOK = "lokacija_tablica";
    private static final String STUPAC_ID_LOK = "id";
    private static final String STUPAC_LAT_LOK = "latituda";
    private static final String STUPAC_LON_LOK = "longituda";
    private static final String STUPAC_VIS_LOK = "visina";
    private static final String STUPAC_PREC_LOK = "preciznost";
    private static final String STUPAC_SMJER_LOK = "smjer";
    private static final String STUPAC_IZVOR_LOK = "izvor";
    private static final String STUPAC_BRZINA_LOK = "brzina";
    private static final String STUPAC_VRIJEME_LOK = "vrijeme";
    private static final String STUPAC_VRIJEME_F_LOK = "vrijeme_f";
    private static final String STUPAC_STAROST_LOK = "starost";
    private static final String STUPAC_VRIJEME_SPREM_LOK = "datum";
    private static final String TABLICA_IME_RUT = "rute_tablica";
    private static final String STUPAC_ID_UNOSA_RUT = "id_unos_rut";
    private static final String STUPAC_ID_RUTE_RUT = "id_rut";
    private static final String STUPAC_ID_LOKACIJE_RUT = "id_lok";
    private static final String STUPAC_TEKST_RUT = "koordinate";
    private static final String STUPAC_DATUM_RUT = "datum";
    public static final String TABLE_CREATE_LOK = "create table "+TABLICA_IME_LOK+" ("+STUPAC_ID_LOK+
            " integer primary key autoincrement, "+STUPAC_LAT_LOK+" text, "+STUPAC_LON_LOK+" text, "
            +STUPAC_VIS_LOK+" text, "+STUPAC_VRIJEME_SPREM_LOK+" text, "+STUPAC_PREC_LOK+" text, "
            +STUPAC_SMJER_LOK+" text, "+STUPAC_IZVOR_LOK+" text, "+STUPAC_BRZINA_LOK+" text, "
            +STUPAC_VRIJEME_LOK+" text, "+STUPAC_VRIJEME_F_LOK+" text, "+STUPAC_STAROST_LOK+" text  );";
    public static final String TABLE_CREATE_RUT = "create table "+TABLICA_IME_RUT+" ("+STUPAC_ID_UNOSA_RUT
            +" integer primary key autoincrement, "+STUPAC_ID_RUTE_RUT+" integer, "
            +STUPAC_ID_LOKACIJE_RUT+" integer, " +STUPAC_TEKST_RUT+" text, "+STUPAC_DATUM_RUT+" text );";
    public MojaBP(Context context) {
        super(context, DATABSE_IME_LOK, null, 1);
        SQLiteDatabase bp = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase bp) {
        bp.execSQL(TABLE_CREATE_LOK);
        bp.execSQL(TABLE_CREATE_RUT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase bp, int oldVersion, int newVersion) {
        String UPIT1 = "DROP TABLE IF EXISTS "+TABLICA_IME_LOK;
        bp.execSQL(UPIT1);
        String UPIT2 = "DROP TABLE IF EXISTS "+TABLICA_IME_RUT;
        bp.execSQL(UPIT2);
        this.onCreate(bp);
    }
    public boolean spremiPodatke_LOK(String lat_s, String lon_s, String vis_s, String vrijeme_sprem,
                                     String acc_s, String bear_s, String prov_s, String speed_s,
                                     String fix_time_s, String fix_time_s_f, String fix_pass_time_s){
        SQLiteDatabase bp = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUPAC_VRIJEME_SPREM_LOK, vrijeme_sprem);
        cv.put(STUPAC_LAT_LOK, lat_s);
        cv.put(STUPAC_LON_LOK, lon_s);
        cv.put(STUPAC_VIS_LOK, vis_s);
        cv.put(STUPAC_PREC_LOK, acc_s);
        cv.put(STUPAC_SMJER_LOK, bear_s);
        cv.put(STUPAC_IZVOR_LOK, prov_s);
        cv.put(STUPAC_BRZINA_LOK, speed_s);
        cv.put(STUPAC_VRIJEME_LOK, fix_time_s);
        cv.put(STUPAC_VRIJEME_F_LOK, fix_time_s_f);
        cv.put(STUPAC_STAROST_LOK, fix_pass_time_s);
        long ishod = bp.insert(TABLICA_IME_LOK, null, cv);
        if(ishod == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean spremiPodatke_RUT(String lat_lon, String vrijeme, Integer id_lok_rut, Integer id_rut){
        SQLiteDatabase bp = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUPAC_TEKST_RUT, lat_lon);
        cv.put(STUPAC_DATUM_RUT, vrijeme);
        cv.put(STUPAC_ID_RUTE_RUT, id_rut);
        cv.put(STUPAC_ID_LOKACIJE_RUT, id_lok_rut);
        long ishod = bp.insert(TABLICA_IME_RUT, null, cv);
        if(ishod == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor dohvatiPodatke_LOK(){
        SQLiteDatabase bp = this.getWritableDatabase();
        Cursor podaci_LOK =  bp.rawQuery("select * from "+TABLICA_IME_LOK,null);
        return podaci_LOK;
    }
    public Cursor dohvatiPodatke_LOK_pojedino(String id){
        SQLiteDatabase bp = this.getWritableDatabase();
        Cursor podaci_LOK =  bp.rawQuery("select * from "+TABLICA_IME_LOK+ " where id=?",new String[] {id});
        return podaci_LOK;
    }
    public Cursor dohvatiPodatke_RUT_list(){
        SQLiteDatabase bp = this.getWritableDatabase();
        Cursor podaci_RUT =  bp.rawQuery("select DISTINCT("+STUPAC_DATUM_RUT+"), "
                +STUPAC_ID_RUTE_RUT+" from "+TABLICA_IME_RUT,null);
        return podaci_RUT;
    }
    public Cursor dohvatiPodatke_RUT_pojedino(String id1, String id2){
        SQLiteDatabase bp = this.getWritableDatabase();
        Cursor podaci_RUT =  bp.rawQuery("select * from " + TABLICA_IME_RUT + " where id_rut=? " +
                "and id_lok=?",  new String[] {id1,id2});
        return podaci_RUT;
    }
    public Cursor max_id_rut(){
        SQLiteDatabase bp = this.getWritableDatabase();
        Cursor max_id_RUT =  bp.rawQuery("select max(id_rut) from "+TABLICA_IME_RUT,null);
        return max_id_RUT;
    }
    public Cursor max_id_rut_lok(){
        SQLiteDatabase bp = this.getWritableDatabase();
        Cursor max_id_RUT_LOK =  bp.rawQuery("select max(id_lok) from "+TABLICA_IME_RUT,null);
        return max_id_RUT_LOK;
    }
    public void obrisiPodatke_LOK(){
        SQLiteDatabase bp = this.getWritableDatabase();
        String UPIT1 = "DROP TABLE IF EXISTS "+TABLICA_IME_LOK;
        bp.execSQL(UPIT1);
        bp.execSQL(TABLE_CREATE_LOK);
    }
    public void obrisiPodatke_RUT(){
        SQLiteDatabase bp = this.getWritableDatabase();
        String UPIT2 = "DROP TABLE IF EXISTS "+TABLICA_IME_RUT;
        bp.execSQL(UPIT2);
        bp.execSQL(TABLE_CREATE_RUT);
    }
    public Integer obrisiStavku_LOK(String id){
        SQLiteDatabase bp = this.getWritableDatabase();
        return bp.delete(TABLICA_IME_LOK, "ID = ?",
                new String[] {id});
    }
    public Integer obrisiStavku_RUT(String id){
        SQLiteDatabase bp = this.getWritableDatabase();
        return bp.delete(TABLICA_IME_RUT, "id_rut = ?",
                new String[] {id});
    }
    public Cursor max_id_rut_LOK2(String id){
        SQLiteDatabase bp = this.getWritableDatabase();
        Cursor max_id_RUT_LOK =  bp.rawQuery("select max(id_lok) from " + TABLICA_IME_RUT +
                " where id_rut=?",  new String[] {id});
        return max_id_RUT_LOK;
    }
}