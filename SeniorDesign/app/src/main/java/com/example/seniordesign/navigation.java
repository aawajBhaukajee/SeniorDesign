package com.example.seniordesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class navigation extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView name;
    Button MainProfile, MainList, MainMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        name=findViewById(R.id.userName);
       name.setText(getIntent().getStringExtra("uname".toString()));

        drawerLayout = findViewById(R.id.drawerLayout);
        MainProfile = findViewById(R.id.mainProfile);
        MainList = findViewById(R.id.mainList);
        MainMap = findViewById(R.id.mainMap);

        MainProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        MainList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),AllHospitals.class));
            }
        });

        MainMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
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