package com.kantinsehat.kantinsehat.Makanan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kantinsehat.kantinsehat.AppController;
import com.kantinsehat.kantinsehat.R;
import com.kantinsehat.kantinsehat.config.ServerAccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Activity_Makanan extends AppCompatActivity {
    ProgressDialog pd;
    TextView nama, harga, kalori, deskripsi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);
        pd = new ProgressDialog(Activity_Makanan.this);
        nama = findViewById(R.id.nama);
        deskripsi = findViewById(R.id.deskripsi);
        harga = findViewById(R.id.harga);
        kalori = findViewById(R.id.kalori);
        loadJson();
    }
    private void loadJson()
    {
        pd.setMessage("Menampilkan Data");
        pd.setCancelable(false);
        pd.show();
        final Intent data = getIntent();
        StringRequest senddata = new StringRequest(Request.Method.GET, ServerAccess.PRODUK+"detail/"+data.getStringExtra("kode"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject res = null;
                try {
                    pd.cancel();
                    res = new JSONObject(response);
//                    JSONArray arr = res.getJSONArray("paket");
                    JSONArray arr = res.getJSONArray("data");
                    JSONObject data = arr.getJSONObject(0);
                    nama.setText(data.getString("nama_produk"));
                    harga.setText(data.getString("harga"));
                    deskripsi.setText(data.getString("deskripsi"));
                    kalori.setText(data.getString("kalori"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    pd.cancel();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "errornya : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(senddata);
    }
}
