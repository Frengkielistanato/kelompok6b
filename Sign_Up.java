package com.kantinsehat.kantinsehat.sign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kantinsehat.kantinsehat.AppController;
import com.kantinsehat.kantinsehat.R;
import com.kantinsehat.kantinsehat.config.ServerAccess;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sign_Up extends AppCompatActivity {
    TextView login;
    Button daftar;
    ProgressDialog pd;
    EditText et_nama, et_username, et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pd = new ProgressDialog(Sign_Up.this);
        et_nama = (EditText)findViewById(R.id.et_nama);
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_Up.this, Sign_In.class));
            }
        });
        daftar = (Button)findViewById(R.id.daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan();
            }
        });
    }
    private void simpan(){
        final String nm= et_nama.getText().toString().trim();
        final String uname= et_username.getText().toString().trim();
        final String pass= et_password.getText().toString().trim();
        if(nm.equals("")){
            Toast.makeText(this, "Nama  Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            et_nama.setFocusable(true);
        }else if(uname.equals("")){
            Toast.makeText(this, "Username Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            et_username.setFocusable(true);
        }else if(pass.equals("")){
            Toast.makeText(this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            et_password.setFocusable(true);
        }else{
            pd.show();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    ServerAccess.REGISTER,
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
                                    startActivity(new Intent(getBaseContext(), Sign_In.class));
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
                    params.put("nama", nm);
                    params.put("username", uname);
                    params.put("password", pass);
                    return params;
                }
            };
            AppController.getInstance().addToRequestQueue(stringRequest);
        }
    }

}
