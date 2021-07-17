package com.example.seniordesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class navigationHospital extends AppCompatActivity {
    DrawerLayout drawerLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_hospital);

        drawerLayout1 = findViewById(R.id.drawerLayout1);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.map:
                        startActivity(new Intent(getApplicationContext()
                                ,MapsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }

    public void ClickMenu1(View view) {

        openDrawer(drawerLayout1);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);

    }

    public void ClickLogo1(View view) {
        closeDrawer(drawerLayout1);
    }

    public static void closeDrawer(DrawerLayout drawerLayout1) {
        if (drawerLayout1.isDrawerOpen(GravityCompat.START)) {

            drawerLayout1.closeDrawer(GravityCompat.START);
        }

    }

    public void ClickHome1(View view) {
        recreate();
    }

    public void ClickProfile1(View view) {
        redirectActivity(this, hospitalProfile.class);
    }

    public void ClickListUsers(View view) {
        redirectActivity(this, AllUsers.class);
    }

    public void ClickLogout1(View view) {
        redirectActivity(this, firstPage.class);
    }

    public static void redirectActivity(Activity activity, Class nav1) {

        Intent intent = new Intent(activity, nav1);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout1);
    }
}