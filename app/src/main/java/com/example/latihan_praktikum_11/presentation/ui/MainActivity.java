package com.example.latihan_praktikum_11.presentation.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.latihan_praktikum_11.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.menu_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.menu_konten) {
                selectedFragment = new KontenFragment();
            } else if (itemId == R.id.menu_tentang) {
                selectedFragment = new TentangFragment();
            } else if (itemId == R.id.menu_setting) {
                selectedFragment = new SettingFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });

        String openFragment = getIntent().getStringExtra("openFragment");
        if (openFragment != null && openFragment.equals("konten")) {
            bottomNavigationView.setSelectedItemId(R.id.menu_konten);
        } else if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.menu_home);
        }
    }
}
