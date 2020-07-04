package com.kantinsehat.kantinsehat.Makanan.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kantinsehat.kantinsehat.Makanan.Activity_Makanan;
import com.kantinsehat.kantinsehat.Makanan.Detail_Makanan;
import com.kantinsehat.kantinsehat.Makanan.Model.Makanan_Model;
import com.kantinsehat.kantinsehat.R;

import java.util.ArrayList;

public class Adapter_Makanan_Dashboard extends RecyclerView.Adapter<Adapter_Makanan_Dashboard.ViewHolder>  {
    private ArrayList<Makanan_Model> listdata;
    private Activity activity;
    private Context context;
    String edit,hapus, detail;
    public Adapter_Makanan_Dashboard(Activity activity, ArrayList<Makanan_Model> listdata, Context context) {
        this.listdata = listdata;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public Adapter_Makanan_Dashboard.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_makanan_ready_dashboard, parent, false);
        Adapter_Makanan_Dashboard.ViewHolder vh = new Adapter_Makanan_Dashboard.ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(Adapter_Makanan_Dashboard.ViewHolder holder, int position) {
        Makanan_Model md = listdata.get(position);
        holder.kode.setText(listdata.get(position).getKode());
        holder.nama.setText(listdata.get(position).getNama());
        holder.kalori.setText(listdata.get(position).getKalori());
        holder.harga.setText(listdata.get(position).getHarga());
        holder.kode.setVisibility(View.GONE);
        holder.mContext = context;
        holder.detailStatus = detail;
    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView kode, nama, kalori, harga;
        String detailStatus;
        Context mContext;
        public ViewHolder(View v) {
            super(v);
            kode=(TextView)v.findViewById(R.id.kode);
            nama=(TextView)v.findViewById(R.id.nama);
            kalori=(TextView)v.findViewById(R.id.kalori);
            harga = (TextView)v.findViewById(R.id.harga);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent;
                        intent = new Intent(v.getContext(), Detail_Makanan.class);
                        intent.putExtra("kode", kode.getText().toString());
                        v.getContext().startActivity(intent);
                    } catch (Exception e) {
                        Log.d("pesan", "error");
                    }
                }
            });
        }
    }
}
