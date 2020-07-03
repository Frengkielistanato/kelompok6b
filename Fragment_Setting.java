package com.kantinsehat.kantinsehat.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kantinsehat.kantinsehat.R;
import com.kantinsehat.kantinsehat.config.AuthData;
import com.kantinsehat.kantinsehat.sign.Sign_In;

public class Fragment_Setting extends Fragment {
    LinearLayout profil, paket, buat_paket, transaksi, bantuan;
    TextView nama, keluar;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        nama = v.findViewById(R.id.nama);
        keluar = v.findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthData.getInstance(getContext()).logout();
                startActivity(new Intent(getContext(), Sign_In.class));
            }
        });
        nama.setText(AuthData.getInstance(getContext()).getNama());
        return v;
    }
}
