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

public class hospitalProfile extends AppCompatActivity {

    TextView hosName,hosEmail,hosLocation,hosProfile;
    Button bButton;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String hospitalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_profile);

        hosProfile = findViewById(R.id.profileH);
        hosName = findViewById(R.id.mainNameH);
        hosEmail = findViewById(R.id.mainEmailH);
        hosLocation = findViewById(R.id.mainLocationH);
        bButton = findViewById(R.id.backButton);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        hospitalId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("hospitals").document(hospitalId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                hosName.setText(documentSnapshot.getString( "HospitalName"));
                hosEmail.setText(documentSnapshot.getString( "HospitalEmail"));
                hosLocation.setText(documentSnapshot.getString("HospitalLocation"));
            }
        });

        bButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Hospital.class));
            }
        });

    }
}