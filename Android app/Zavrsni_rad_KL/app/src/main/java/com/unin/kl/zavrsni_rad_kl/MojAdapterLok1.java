package com.unin.kl.zavrsni_rad_kl;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
public class MojAdapterLok1 extends ArrayAdapter<String> {
    private ArrayList<String> podaci2;
    public MojAdapterLok1 (Context context, int resource, ArrayList<String> podaci2) {
        super(context, resource, podaci2);
        this.podaci2 = podaci2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.moj_layout_lokacije_pov1, null);
        }
        String podatak=this.podaci2.get(position);
        String split1[] = podatak.split("\\?");
        String vrijeme = split1[1];
        TextView brojPrikaz_tv=(TextView)view.findViewById(R.id.brojPrikaz1);
        brojPrikaz_tv.setText(Integer.toString(position + 1));
        TextView datumPrikaz_tv=(TextView)view.findViewById(R.id.datumPrikaz1);
        datumPrikaz_tv.setText(vrijeme);
        return view;
    }
}