/*References
*   https://youtu.be/tPV8xA7m-iw - Open navigation panel Items as Fragments
*/

package com.edu.wellwork;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //Default page is Home page now
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new fragment_home()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        FirebaseAuth.getInstance().signOut();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.home:
                    selectedFragment = new fragment_home();
                    break;
                case R.id.prescription:
                    selectedFragment = new fragment_prescription();
                    break;
                case R.id.cart:
                    selectedFragment = new fragment_cart();
                    break;
                case R.id.feedback:
                    selectedFragment = new fragment_reviews();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };
}