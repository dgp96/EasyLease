package com.example.easylease;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById((R.id.bottom_navigation));

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_maintenance:
                        //fragment = fragment1;
                        Toast.makeText(MainActivity.this, "Maintenance", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_schedule:
                        Toast.makeText(MainActivity.this, "Schedule", Toast.LENGTH_SHORT).show();
                        //fragment = fragment2;
                        break;
                    case R.id.action_notification:
                        Toast.makeText(MainActivity.this, "Notifications", Toast.LENGTH_SHORT).show();
                        //fragment = fragment3;
                        break;
                    case R.id.action_profile:

                    default:
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                        //fragment = fragment4;
                        break;
                }
                //fragmentManager.beginTransaction().replace(R.id.rlContainer, fragment).commit();
                return true;
            }
        });

    }
}