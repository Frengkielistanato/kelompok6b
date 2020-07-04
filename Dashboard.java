package com.kantinsehat.kantinsehat.Dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kantinsehat.kantinsehat.R;

public class Dashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(Dashboard.this);
        navigation.setSelectedItemId(R.id.home);
        loadFragment(new Fragment_Dashboard());
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.dashboard:
                fragment = new Fragment_Dashboard();
                break;
            case R.id.profil:
                fragment = new Fragment_Setting();
                break;
            case R.id.tiket:
                fragment = new Fragment_Makanan();
                break;

        }
        return loadFragment(fragment);
    }
    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.logo_gklf)
                .setTitle("Keluar Akun")
                .setMessage("Apakah Anda Yakin Ingin Keluar Dari Akun Ini ?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        AuthData.getInstance(getBaseContext()).logout();
//                        startActivity(new Intent(getBaseContext(), Sign_In.class));
                    }
                })
                .setNegativeButton("Tidak", null)
                .show();
    }
}
