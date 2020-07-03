package com.kantinsehat.kantinsehat.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kantinsehat.kantinsehat.AppController;
import com.kantinsehat.kantinsehat.Makanan.Adapter.Adapter_Makanan;
import com.kantinsehat.kantinsehat.Makanan.Model.Makanan_Model;
import com.kantinsehat.kantinsehat.R;
import com.kantinsehat.kantinsehat.config.ServerAccess;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Makanan extends Fragment {
    private Adapter_Makanan adapter;
    private List<Makanan_Model> list;
    private RecyclerView listdata;
    RecyclerView.LayoutManager mManager;
    SwipeRefreshLayout swLayout;
    private GridLayoutManager layoutManager;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_makanan, container, false);
        listdata = (RecyclerView) v.findViewById(R.id.listdata);
//        not_found = findViewById(R.id.not_found);
        listdata.setHasFixedSize(true);
        list = new ArrayList<>();
        adapter = new Adapter_Makanan(getActivity(),(ArrayList<Makanan_Model>) list, getContext());
//        mManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setOrientation(GridLayoutManager.VERTICAL);
        listdata.setLayoutManager(layoutManager);
        listdata.setAdapter(adapter);
        swLayout = (SwipeRefreshLayout) v.findViewById(R.id.swlayout);
        swLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reload();
            }
        });
        loadJson();
        return v;
    }
    public void reload(){
//        not_found.setVisibility(View.GONE);
        list.clear();
        loadJson(); // your code
        listdata.getAdapter().notifyDataSetChanged();
        swLayout.setRefreshing(false);
    }
    private void loadJson()
    {
        String link = ServerAccess.PRODUK;
        StringRequest senddata = new StringRequest(Request.Method.GET, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject res = null;
                try {
                    res = new JSONObject(response);
                    JSONArray arr = res.getJSONArray("data");
                    if(arr.length() > 0) {
                        for (int i = 0; i < arr.length(); i++) {
                            try {
                                JSONObject data = arr.getJSONObject(i);
                                Makanan_Model md = new Makanan_Model();
                                md.setKode(data.getString("id"));
                                md.setNama(data.getString("nama_produk"));
                                md.setNama(data.getString("nama_produk"));
                                md.setHarga(data.getString("harga"));
                                md.setKalori(data.getString("kalori"));
                                md.setHarga(ServerAccess.numberConvert(data.getString("harga")));
                                md.setCover(ServerAccess.BASE_URL+"gambar/product/"+ data.getString("image"));
                                list.add(md);
                            } catch (Exception ea) {
                                ea.printStackTrace();
                                Log.d("pesan", ea.getMessage());
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }else{
//                        not_found.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                    Log.d("pesan", "error "+e.getMessage());
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                        Log.d("volley", "errornya : " + error.getMessage());
                    }
                });
        AppController.getInstance().addToRequestQueue(senddata);
    }
}
