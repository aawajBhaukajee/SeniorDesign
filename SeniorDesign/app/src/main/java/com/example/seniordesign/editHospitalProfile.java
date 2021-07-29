package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class editHospitalProfile extends AppCompatActivity {

    public static final String TAG = "TAG";

    DrawerLayout drawerLayout1;
    TextView ApplicationNameH;
    EditText EditNameH, EditEmailH,EditLocationH;
    Button SaveButtonH,backButton;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hospital_profile);

        drawerLayout1 = findViewById(R.id.drawerLayout1);
        ApplicationNameH = findViewById(R.id.editProfileH);
        EditNameH = findViewById(R.id.editNameH);
        EditEmailH = findViewById(R.id.editEmailH);
        EditLocationH = findViewById(R.id.editLocation);

        SaveButtonH = findViewById(R.id.save_buttonH);
        backButton = findViewById(R.id.btoprofile);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();

        Intent data = getIntent();
        String HospitalName = data.getStringExtra("HospitalName");
        String HospitalEmail = data.getStringExtra("HospitalEmail");
        String HospitalLocation = data.getStringExtra("HospitalLocation");

        EditNameH.setText(HospitalName);
        EditEmailH.setText(HospitalEmail);
        EditLocationH.setText(HospitalLocation);


        SaveButtonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(EditNameH.getText().toString().isEmpty()||
                        EditEmailH.getText().toString().isEmpty()||
                        EditLocationH.getText().toString().isEmpty())
                {
                    Toast.makeText(editHospitalProfile.this, "FIELDS ARE EMPTY", Toast.LENGTH_SHORT).show();
                    return;
                }

                String email = EditEmailH.getText().toString();

                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference ref = firebaseFirestore.collection("hospitals").document(user.getUid());
                        Map<String,Object> e = new HashMap<>();
                        e.put("HospitalEmail",email);
                        e.put("HospitalName",EditNameH.getText().toString());
                        e.put("HospitalLocation",EditLocationH.getText().toString());

                        ref.update(e).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                Toast.makeText(editHospitalProfile.this, "Update Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(editHospitalProfile.this,hospitalProfile.class));
                                finish();
                            }
                        });
                        Toast.makeText(editHospitalProfile.this, "Change Successful",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {
                        Toast.makeText(editHospitalProfile.this, e.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),hospitalProfile.class));
            }
        });

    }

    }

/*
    public void ClickMenu1(View view){
        navigationHospital.openDrawer(drawerLayout1);
    }

    public void ClickLogo1(View view){
        navigationHospital.closeDrawer(drawerLayout1);
    }

    public void ClickHome1(View view){
        navigationHospital.redirectActivity(this,navigationHospital.class);
    }


    public void ClickProfile1(View view){
        navigationHospital.redirectActivity(this,hospitalProfile.class);
    }

    public void ClickListUsers(View view){
        recreate();
    }

    public void ClickLogout1(View view){
        navigationHospital.redirectActivity(this, firstPage.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigation.closeDrawer(drawerLayout1);
    }
*/
