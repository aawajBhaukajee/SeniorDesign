package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class  MainActivity extends AppCompatActivity {


    DrawerLayout drawerLayout;

    TextView fullname,email,hbp,lbp,btype,mainage,mainweight,Profile;
    Button EditProfile;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawerLayout);

        EditProfile=findViewById(R.id.profileEdit);
        Profile = findViewById(R.id.profile);
        fullname = findViewById(R.id.mainName);
        email = findViewById(R.id.mainEmail);
        hbp = findViewById(R.id.mainHBP);
        lbp = findViewById(R.id.mainLBP);
        btype = findViewById(R.id.mainbloodType);
        mainage = findViewById(R.id.mainAge);
        mainweight = findViewById(R.id.mainWeight);
        // hosList = findViewById(R.id.hospList);
        //Welcome = findViewById(R.id.welcome);
        //LogoutButton = findViewById(R.id.logoutButton);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();



        DocumentReference documentReference = fStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                fullname.setText(documentSnapshot.getString( "FullName"));
                email.setText(documentSnapshot.getString( "EmailAddress"));
                hbp.setText(documentSnapshot.getString( "MaximumBP"));
                lbp.setText(documentSnapshot.getString( "MinimumBP"));
                btype.setText(documentSnapshot.getString( "BloodType"));
                mainage.setText(documentSnapshot.getString( "Age"));
                mainweight.setText(documentSnapshot.getString( "Weight"));

            }
        });

        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(v.getContext(), EditProfile.class);
                start.putExtra("FullName",fullname.getText().toString());
                start.putExtra("EmailAddress",email.getText().toString());
                start.putExtra("MaximumBP",hbp.getText().toString());
                start.putExtra("MinimumBP",lbp.getText().toString());
                start.putExtra("BloodType",btype.getText().toString());
                start.putExtra("Age",mainage.getText().toString());
                start.putExtra("Weight",mainweight.getText().toString());
                startActivity(start);

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,navigation.class));
                        overridePendingTransition(0,0);
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
    public void ClickMenu(View view){
        navigation.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        navigation.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        navigation.redirectActivity(this,navigation.class);
    }

    public void ClickProfile(View view){
        recreate();
    }

    public void ClickListHospital(View view){
        navigation.redirectActivity(this,AllHospitals.class);
    }

    public void ClickMaps(View view){
        navigation.redirectActivity(this,MapsActivity.class);
    }
    public void ClickLogout(View view){
        navigation.redirectActivity(this, firstPage.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigation.closeDrawer(drawerLayout);
    }
}