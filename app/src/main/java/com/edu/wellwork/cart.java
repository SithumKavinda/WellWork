package com.edu.wellwork;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class cart extends AppCompatActivity {

    BottomNavigationView botNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //Initialize Variables
        botNav = findViewById(R.id.bottom_navigation);

        //set home selected
        botNav.setSelectedItemId(R.id.cart);

        //perform ItemSelectedListener
        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.prescription:
                        startActivity(new Intent(getApplicationContext(), prescription.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.cart:
                        return true;

                    case R.id.feedback:
                        startActivity(new Intent(getApplicationContext(), feedback.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }
}