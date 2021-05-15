package com.unin.kl.zavrsni_rad_kl;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
public class MojRecyclerViewAdapter3 extends RecyclerView.Adapter<MojRecyclerViewAdapter3.ViewHolder> {
    private List<String> urlLista;
    private LayoutInflater mojInflater;
    MojRecyclerViewAdapter3(Context context, List<String> slike) {
        this.mojInflater = LayoutInflater.from(context);
        this.urlLista = slike;
    }
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mojInflater.inflate(R.layout.slika1, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String slika = urlLista.get(position);
        Picasso.get().load(slika).into(holder.mojIv);
    }
    @Override
    public int getItemCount() {
        return urlLista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mojIv;
        ViewHolder(View v) {
            super(v);
            mojIv = v.findViewById(R.id.imageView1);
        }
    }
}