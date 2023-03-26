package com.example.gpshelp.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gpshelp.R;
import com.example.gpshelp.fragments.AboutFragment;
import com.example.gpshelp.fragments.MainFragment;
import com.example.gpshelp.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    MainFragment mainFragment = new MainFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mainButton = findViewById(R.id.b_map);
        Button aboutButton = findViewById(R.id.b_about);
        Button profileButton = findViewById(R.id.b_profile);

        setNewFragment(mainFragment);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewFragment(mainFragment);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutFragment aboutFragment = new AboutFragment();
                setNewFragment(aboutFragment);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment profileFragment = new ProfileFragment();
                setNewFragment(profileFragment);
            }
        });
    }

    private void setNewFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}