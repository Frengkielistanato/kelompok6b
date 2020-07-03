package com.kantinsehat.kantinsehat.sign;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kantinsehat.kantinsehat.AppController;
import com.kantinsehat.kantinsehat.Dashboard.Dashboard;
import com.kantinsehat.kantinsehat.R;
import com.kantinsehat.kantinsehat.config.AuthData;
import com.kantinsehat.kantinsehat.config.ServerAccess;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sign_In extends AppCompatActivity {
    EditText et_password, et_username;
    Button masuk, daftar;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        pd = new ProgressDialog(Sign_In.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        et_password = (EditText)findViewById(R.id.et_password);
        et_username = (EditText)findViewById(R.id.et_username);
        daftar = findViewById(R.id.daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_In.this, Sign_Up.class));
            }
        });
        masuk = (Button)findViewById(R.id.masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        onLogin();
    }
    private void onLogin(){
        if(AuthData.getInstance(this).isLoggedIn()){
            Sign_In.this.finish();
            startActivity(new Intent(getBaseContext(), Dashboard.class));
        }
    }
    private void login(){
        final String uname= et_username.getText().toString().trim();
        final String pass= et_password.getText().toString().trim();
        if(uname.equals("")){
            Toast.makeText(this, "Username Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
            et_username.setFocusable(true);
        }else{
            pd.show();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    ServerAccess.auth,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            pd.dismiss();
                            try {
                                JSONObject obj = new JSONObject(response);
                                Log.d("pesan", response);
                                JSONObject data = obj.getJSONObject("data");
                                if (obj.getBoolean("status")) {
                                    Toast.makeText(
                                            getBaseContext(),
                                            "Berhasil Login",
                                            Toast.LENGTH_LONG
                                    ).show();
                                    AuthData.getInstance(getBaseContext()).setdatauser(data.getString("id"), data.getString("nama"));
                                    startActivity(new Intent(getBaseContext(), Dashboard.class));
                                } else {
                                    Toast.makeText(
                                            getBaseContext(),
                                            "Gagal Login",
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
                                    "Gagal Login",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", uname);
                    params.put("password", pass);
                    return params;
                }
            };
            AppController.getInstance().addToRequestQueue(stringRequest);
        }
    }

}
