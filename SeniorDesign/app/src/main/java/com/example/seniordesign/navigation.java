package com.example.seniordesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class navigation extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView sdate, stime, i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        sdate = findViewById(R.id.showdate);
        stime = findViewById(R.id.showtime);
        i = findViewById(R.id.i);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem menuItem) {
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
        drawerLayout = findViewById(R.id.drawerLayout);


        sdate.setText(calendar.getD());
        stime.setText(calendar2.getT());
    }




    public void ClickMenu(View view) {

        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);

    }

    public void ClickLogo(View view) {
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);
        }

    }

    public void ClickHome(View view) {
        recreate();
    }

    public void ClickProfile(View view) {
        redirectActivity(this, MainActivity.class);
    }

    public void ClickListHospital(View view) {
        redirectActivity(this, AllHospitals.class);
    }

    public void ClickMap(View view) { redirectActivity(this, MapsActivity.class);}

    public void ClickLogout(View view) {
        redirectActivity(this, firstPage.class);
    }


    public static void redirectActivity(Activity activity, Class nav) {

        Intent intent = new Intent(activity, nav);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }
}