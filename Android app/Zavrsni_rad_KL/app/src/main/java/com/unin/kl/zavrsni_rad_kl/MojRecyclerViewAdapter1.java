package com.unin.kl.zavrsni_rad_kl;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class MojRecyclerViewAdapter1 extends RecyclerView.Adapter<MojRecyclerViewAdapter1.ViewHolder> {
    private List<Uri> uriLista;
    private LayoutInflater mojInflater;
    private ItemLongClickListener mojClickListener;
    MojRecyclerViewAdapter1(Context context, List<Uri> slike) {
        this.mojInflater = LayoutInflater.from(context);
        this.uriLista = slike;
    }
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mojInflater.inflate(R.layout.slika1, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri slika = uriLista.get(position);
        holder.mojIv.setImageURI(slika);
    }
    @Override
    public int getItemCount() {
        return uriLista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        ImageView mojIv;
        ViewHolder(View v) {
            super(v);
            mojIv = v.findViewById(R.id.imageView1);
            v.setOnLongClickListener(this);
        }
        @Override
        public boolean onLongClick(View view) {
            if (mojClickListener != null){
                mojClickListener.onItemClick(view, getAdapterPosition());}
            return false;
        }
    }
    public void getItem(int id) {
        return;
    }
    public void setLongClickListener(Prijava_Activity itemLongClickListener) {
        this.mojClickListener = itemLongClickListener;
    }
    public interface ItemLongClickListener {
        void onItemClick(View view, int position);
    }
}