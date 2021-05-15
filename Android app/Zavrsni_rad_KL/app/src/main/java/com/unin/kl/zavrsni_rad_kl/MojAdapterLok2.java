package com.unin.kl.zavrsni_rad_kl;
import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
public class MojAdapterLok2 extends ArrayAdapter<String> {
    String id;
    private ArrayList<String> podaci3;
    public MojAdapterLok2 (Context context, int resource, ArrayList<String> podaci3) {
        super(context, resource, podaci3);
        this.podaci3 = podaci3;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.moj_layout_lokacije_pov2, null);
        }
        String podatak=this.podaci3.get(position);
        String split1[] = podatak.split("\\?");
        String vrijeme = split1[1];
        String split2[] = split1[0].split("\\&");
        String split3[] = split2[0].split("\\*");
        Location location = new Location("");
        location.setLatitude(Double.parseDouble(split3[1]));
        location.setLongitude(Double.parseDouble(split2[1]));
        String lat_pr = String.valueOf(location.convert(location.getLatitude(),location.FORMAT_SECONDS));
        String lon_pr = String.valueOf(location.convert(location.getLongitude(),location.FORMAT_SECONDS));
        TextView datumPrikaz_tv=(TextView)view.findViewById(R.id.datumPrikaz2);
        datumPrikaz_tv.setText(vrijeme);
        TextView lokPrikaz_tv=(TextView)view.findViewById(R.id.lokPrikaz);
        lokPrikaz_tv.setText("Lat: "+lat_pr+"\n"+"Long: "+lon_pr);
        return view;
    }
}