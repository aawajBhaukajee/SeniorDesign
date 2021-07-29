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

public class EditProfile extends AppCompatActivity {

    public static final String TAG = "TAG";

    DrawerLayout drawerLayout;
    TextView ApplicationName;
    EditText EditName, EditEmail,EditMinimumBP, EditMaximumBP, EditAge, EditWeight, EditBloodType;
    Button SaveButton,back;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        drawerLayout = findViewById(R.id.drawerLayout);

        ApplicationName = findViewById(R.id.editProfile);
        EditName = findViewById(R.id.editName);
        EditEmail = findViewById(R.id.editEmail);
        EditMinimumBP = findViewById(R.id.editLBP);
        EditMaximumBP = findViewById(R.id.editHBP);
        EditAge = findViewById(R.id.editAge);
        EditWeight = findViewById(R.id.editWeight);
        EditBloodType = findViewById(R.id.editBloodType);
        SaveButton = findViewById(R.id.save_button);
        back = findViewById(R.id.backtoprofile);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();


        Intent data = getIntent();
        String FullName = data.getStringExtra("FullName");
        String EmailAddress = data.getStringExtra("EmailAddress");
        String MaximumBP = data.getStringExtra("MaximumBP");
        String MinimumBP = data.getStringExtra("MinimumBP");
        String BloodType = data.getStringExtra("BloodType");
        String Age = data.getStringExtra("Age");
        String Weight = data.getStringExtra("Weight");


        EditName.setText(FullName);
        EditEmail.setText(EmailAddress);
        EditMinimumBP.setText(MinimumBP);
        EditMaximumBP.setText(MaximumBP);
        EditAge.setText(Age);
        EditWeight.setText(Weight);
        EditBloodType.setText(BloodType);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(EditName.getText().toString().isEmpty()||
                EditEmail.getText().toString().isEmpty()||
                EditMaximumBP.getText().toString().isEmpty()||
                EditMinimumBP.getText().toString().isEmpty()||
                EditAge.getText().toString().isEmpty()||
                EditWeight.getText().toString().isEmpty()||
                EditBloodType.getText().toString().isEmpty())
                {
                    Toast.makeText(EditProfile.this, "FIELDS ARE EMPTY", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email = EditEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference ref = firebaseFirestore.collection("users").document(user.getUid());
                        Map<String,Object> e = new HashMap<>();
                        e.put("email",email);
                        e.put("FullName",EditName.getText().toString());
                        e.put("MaximumBP",EditMaximumBP.getText().toString());
                        e.put("MinimumBP",EditMinimumBP.getText().toString());
                        e.put("Age",EditAge.getText().toString());
                        e.put("Weight",EditWeight.getText().toString());
                        e.put("BloodType",EditBloodType.getText().toString());
                        ref.update(e).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditProfile.this, "Update Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(EditProfile.this,MainActivity.class));
                                finish();
                            }
                        });

                        Toast.makeText(EditProfile.this, "Change Successful",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfile.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

    }

    /*public void ClickMenu(View view){
        navigation.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view){
        navigation.closeDrawer(drawerLayout);
    }

    public void ClickHome(View view){
        navigation.redirectActivity(this,navigation.class);
    }

    public void ClickEditProfile(View view) {
        recreate();
    }

    public void ClickProfile(View view){
       navigation.redirectActivity(this,MainActivity.class);
    }

    public void ClickListHospital(View view){
        navigation.redirectActivity(this,AllHospitals.class);
    }

    public void ClickLogout(View view){
        navigation.redirectActivity(this, firstPage.class);
    }

    @Override
    protected void onPause() {
        super.onPause();
        navigation.closeDrawer(drawerLayout);
    }

     */
}
