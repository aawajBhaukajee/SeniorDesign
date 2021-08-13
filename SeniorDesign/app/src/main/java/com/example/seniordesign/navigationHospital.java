package com.example.seniordesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class navigationHospital extends AppCompatActivity {
    DrawerLayout drawerLayout1;
    TextView snameH, sdateH, stimeH,iH;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button donated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_hospital);

        drawerLayout1 = findViewById(R.id.drawerLayout1);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation1);
        bottomNavigationView.setSelectedItemId(R.id.hhome);

        snameH = findViewById(R.id.shownameH);
        sdateH = findViewById(R.id.showdateH);
        stimeH = findViewById(R.id.showtimeH);
        donated = findViewById(R.id.wDonated);
        iH = findViewById(R.id.iH);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();


        DocumentReference documentReference = fStore.collection("hospitals").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                snameH.setText(documentSnapshot.getString("Scheduled by:"));
                sdateH.setText(documentSnapshot.getString("Date Requested:"));
                stimeH.setText(documentSnapshot.getString("Time Requested:"));

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.hprofile:
                        startActivity(new Intent(getApplicationContext()
                                ,hospitalProfile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.hhome:
                        return true;
                    case R.id.hdonors:
                        startActivity(new Intent(getApplicationContext()
                                ,AllUsers.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        donated.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), com.example.seniordesign.whoDonated.class));
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