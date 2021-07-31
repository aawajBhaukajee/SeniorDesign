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
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class navigation extends AppCompatActivity {
    DrawerLayout drawerLayout;
    TextView sdate, stime,sname, i, c, totalCount;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button d,d1;
    int a=  0;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        sdate = findViewById(R.id.showdate);
        stime = findViewById(R.id.showtime);
        sname = findViewById(R.id.showname);
        i = findViewById(R.id.i);
        c = findViewById(R.id.num);
        totalCount = findViewById(R.id.count);
        d = findViewById(R.id.bDone);
        d1 = findViewById(R.id.bNotdone);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                sdate.setText(documentSnapshot.getString( "Date"));
                stime.setText(documentSnapshot.getString( "Time"));
                sname.setText(documentSnapshot.getString("Schedule Request at:"));
                totalCount.setText(documentSnapshot.getString("TotalDonation"));

            }
        });

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

        //   sdate.setText(calendar.getD());
        //   stime.setText(calendar2.getT());

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                a++;
                totalCount.setText(""+ a);
                final String counter = totalCount.getText().toString().trim();

                // final String counter = totalCount.getText().toString().trim();
                //a++;
                //totalCount.setText(""+ a);

                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String, Object> update = new HashMap<>();
                update.put("TotalDonation", counter);
                fStore.collection("users").document(userID).set(update, SetOptions.merge());


                DocumentReference dref = fStore.collection("users").document(userID);
                Map<String, Object> delete = new HashMap<>();
                delete.put("Date", FieldValue.delete());
                delete.put("Time", FieldValue.delete());
                delete.put("Schedule Request at:",FieldValue.delete());
                fStore.collection("users").document(userID).update(delete);

                d.setVisibility(View.GONE);
                d1.setVisibility(View.GONE);
                sdate.setVisibility(View.GONE);
                stime.setVisibility(View.GONE);


            }
        });

        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = fAuth.getCurrentUser().getUid();
                DocumentReference dref = fStore.collection("users").document(userID);
                Map<String, Object> delete = new HashMap<>();
                delete.put("Date", FieldValue.delete());
                delete.put("Time", FieldValue.delete());
                delete.put("Schedule Request at:",FieldValue.delete());
                fStore.collection("users").document(userID).update(delete);

                d1.setVisibility(View.GONE);
                sdate.setVisibility(View.GONE);
                stime.setVisibility(View.GONE);
                d.setVisibility(View.GONE);
            }
        });

    }



  /*  public void increase(View v){
        a++;
        totalCount.setText(""+ a);
   */


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