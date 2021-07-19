package com.example.seniordesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class navigation extends AppCompatActivity {
    DrawerLayout drawerLayout;
 Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        drawerLayout = findViewById(R.id.drawerLayout);
        b1 = findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),calendar_schedule.class));
            }
        });
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