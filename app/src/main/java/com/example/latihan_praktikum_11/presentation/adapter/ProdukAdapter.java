package com.example.latihan_praktikum_11.presentation.adapter;

import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.latihan_praktikum_11.R;
import com.example.latihan_praktikum_11.data.entity.Produk;

import java.util.ArrayList;
import java.util.List;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder> {
    private List<Produk> produkList;

    public ProdukAdapter(List<Produk> produkList) {
        this.produkList = produkList != null ? produkList : new ArrayList<>();
    }

    public void setProdukList(List<Produk> produkList) {
        this.produkList = produkList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Produk produk = produkList.get(position);
        holder.tvNama.setText(produk.getTitle());
        holder.tvHarga.setText("Rp " + produk.getPrice());

        String imagePath = produk.getImage();

        if (!TextUtils.isEmpty(imagePath)) {
            Glide.with(holder.itemView.getContext())
                    .load(imagePath.startsWith("content://") ? Uri.parse(imagePath) : imagePath)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .into(holder.idGambar);
        } else {
            holder.idGambar.setImageResource(R.drawable.placeholder);
        }
    }

    @Override
    public int getItemCount() {
        return produkList != null ? produkList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvHarga;
        ImageView idGambar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            idGambar = itemView.findViewById(R.id.idGambar);
        }
    }
}
