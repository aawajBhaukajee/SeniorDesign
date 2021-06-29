package com.example.seniordesign;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class hospitalProfile extends AppCompatActivity {

    TextView hosName, hosEmail, hosLocation, hosProfile;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String hospitalId;
    DrawerLayout drawerLayout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_profile);

        drawerLayout1 = findViewById(R.id.drawerLayout1);

        hosProfile = findViewById(R.id.profileH);
        hosName = findViewById(R.id.mainNameH);
        hosEmail = findViewById(R.id.mainEmailH);
        hosLocation = findViewById(R.id.mainLocationH);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        hospitalId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("hospitals").document(hospitalId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                hosName.setText(documentSnapshot.getString("HospitalName"));
                hosEmail.setText(documentSnapshot.getString("HospitalEmail"));
                hosLocation.setText(documentSnapshot.getString("HospitalLocation"));
            }
        });

    }

    public void ClickMenu1(View view) {
        navigationHospital.openDrawer(drawerLayout1);
    }

    public void ClickLogo1(View view) {
        navigationHospital.closeDrawer(drawerLayout1);
    }

    public void ClickHome1(View view) {
        navigationHospital.redirectActivity(this, navigationHospital.class);
    }

    public void ClickProfile1(View view) {
        recreate();
    }

    public void ClickListUsers(View view) {
        navigationHospital.redirectActivity(this, AllUsers.class);
    }

    public void ClickLogout1(View view) {
        navigationHospital.redirectActivity(this, firstPage.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigationHospital.closeDrawer(drawerLayout1);

    }


}