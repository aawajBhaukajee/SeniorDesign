package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    TextView fullname,email,hbp,lbp,btype,mainage,mainweight,Welcome,Profile;
    Button LogoutButton, hosList;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Profile = findViewById(R.id.profile);
        fullname = findViewById(R.id.mainName);
        email = findViewById(R.id.mainEmail);
        hbp = findViewById(R.id.mainHBP);
        lbp = findViewById(R.id.mainLBP);
        btype = findViewById(R.id.mainbloodType);
        mainage = findViewById(R.id.mainAge);
        mainweight = findViewById(R.id.mainWeight);
        hosList = findViewById(R.id.hospList);
        Welcome = findViewById(R.id.welcome);
        LogoutButton = findViewById(R.id.logoutButton);
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

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),firstPage.class));
            }
        });

        hosList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AllHospitals.class));
            }
        });

    }
}