package com.unin.kl.zavrsni_rad_kl;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class MojRecyclerViewAdapter2 extends RecyclerView.Adapter<MojRecyclerViewAdapter2.ViewHolder> {
    private List<String> stavkeLista;
    private LayoutInflater mojInflater;
    private ItemClickListener mojClickListener;
    MojRecyclerViewAdapter2(Context context, List<String> datumi) {
        this.mojInflater = LayoutInflater.from(context);
        this.stavkeLista = datumi;
    }
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mojInflater.inflate(R.layout.povstavka, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String tekst = stavkeLista.get(position);
        holder.tvDatum.setText(tekst);
    }
    @Override
    public int getItemCount() {
        return stavkeLista.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvDatum;
        ViewHolder(View v) {
            super(v);
            tvDatum = v.findViewById(R.id.tv_datum);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mojClickListener != null){
                mojClickListener.onItemClick(view, getAdapterPosition());}
        }
    }
    public void getItem(int id) {
        return;
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mojClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}