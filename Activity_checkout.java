package com.kantinsehat.kantinsehat.transaksi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kantinsehat.kantinsehat.AppController;
import com.kantinsehat.kantinsehat.Dashboard.Dashboard;
import com.kantinsehat.kantinsehat.Makanan.Detail_Makanan;
import com.kantinsehat.kantinsehat.R;
import com.kantinsehat.kantinsehat.config.AuthData;
import com.kantinsehat.kantinsehat.config.ServerAccess;
import com.kantinsehat.kantinsehat.sign.Sign_In;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Activity_checkout extends AppCompatActivity {
    ImageView tambah,kurangi;
    TextView stok, nama, harga, total;
    ImageView iv_back;
    EditText bayar;
    ProgressDialog pd;
    Button btn_home, lanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        pd = new ProgressDialog(Activity_checkout.this);
        tambah = findViewById(R.id.imageView9);
        stok = findViewById(R.id.textView11);
        iv_back = findViewById(R.id.iv_back);
        harga = findViewById(R.id.textView23);
        total = findViewById(R.id.textView13);
        bayar = findViewById(R.id.bayar);
        btn_home = findViewById(R.id.btn_home);
        lanjut = findViewById(R.id.button5);
        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Dashboard.class));
            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tk = Integer.parseInt(stok.getText().toString())+1;
                stok.setText(Integer.toString(tk));
                int t_total = Integer.parseInt(harga.getText().toString()) * tk;
                total.setText(Integer.toString(t_total));
            }
        });
        kurangi = findViewById(R.id.imageView8);
        kurangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tk = Integer.parseInt(stok.getText().toString())-1;
                if(tk != 0){
                    stok.setText(Integer.toString(tk));
                    int t_total = Integer.parseInt(harga.getText().toString()) * tk;
                    total.setText(Integer.toString(t_total));
                }
            }
        });
        nama = findViewById(R.id.textView21);

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
                    total.setText(data.getString("harga"));
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
    private void simpan(){
        Intent data = getIntent();
        final String kode = data.getStringExtra("kode");
        final String jumlah = stok.getText().toString();
        final String by = bayar.getText().toString().trim();
        if(kode.equals("")){
            Toast.makeText(this, "Tidak Valid", Toast.LENGTH_SHORT).show();

        }else if(jumlah.equals("")){
            Toast.makeText(this, "Jumlah stok Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        }else if(by.equals("")){
            Toast.makeText(this, "Pembayaran Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            bayar.setFocusable(true);
        }else if(Integer.parseInt(by) < Integer.parseInt(total.getText().toString())){
            Toast.makeText(this, "Uang Pembayaran Kurang ", Toast.LENGTH_SHORT).show();
            bayar.setFocusable(true);
        }else{
            pd.show();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    ServerAccess.CHECKOUT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pd.dismiss();
                            try {
                                JSONObject obj = new JSONObject(response);
//                                JSONObject data = obj.getJSONObject("respon");
                                if (obj.getBoolean("status")) {
                                    Toast.makeText(
                                            getBaseContext(),
                                            obj.getString("message"),
                                            Toast.LENGTH_LONG
                                    ).show();
                                    startActivity(new Intent(getBaseContext(), Dashboard.class));
                                } else {
                                    Toast.makeText(
                                            getBaseContext(),
                                            obj.getString("message"),
                                            Toast.LENGTH_LONG
                                    ).show();
                                }
                            } catch (JSONException e) {
                                Toast.makeText(
                                        getBaseContext(),
                                        e.getMessage(),
                                        Toast.LENGTH_LONG
                                ).show();
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pd.dismiss();
                            Toast.makeText(
                                    getBaseContext(),
                                    "error",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("barcode", kode);
                    params.put("qty", jumlah);
                    params.put("jumlah_uang", by);
                    params.put("pelanggan", AuthData.getInstance(getBaseContext()).getId_user());
                    return params;
                }
            };
            AppController.getInstance().addToRequestQueue(stringRequest);
        }
    }
}
