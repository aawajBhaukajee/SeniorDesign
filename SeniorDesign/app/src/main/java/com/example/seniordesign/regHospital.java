package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class regHospital extends AppCompatActivity {

    public static final String TAG = "TAG";

    TextView AppNameH, RegisterTitleH, OldAccountH;
    EditText FullNameH, EmailH, LocationH,PasswordH;
    Button RegisterButtonH;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String hospitalID;
    private DatabaseReference UserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_hospital);

        AppNameH = findViewById(R.id.appNameH);
        RegisterTitleH = findViewById(R.id.regTitleH);
        OldAccountH = findViewById(R.id.hasAccountH);
        FullNameH = findViewById(R.id.nameH);
        EmailH = findViewById(R.id.emailAddressH);
        LocationH = findViewById(R.id.locationH);
        PasswordH = findViewById(R.id.passwordH);
        RegisterButtonH = findViewById(R.id.registerButtonH);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        /*if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),com.example.seniordesign.register.class));
            finish();
        }
         */
        RegisterButtonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String fullname = FullNameH.getText().toString().trim();
                final String email = EmailH.getText().toString();
                final String location = LocationH.getText().toString().trim();
                final String password = PasswordH.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    EmailH.setError("Email is needed to register.");
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    PasswordH.setError("Password is needed to register.");
                    return;
                }
                if (password.length() < 8)
                {
                    PasswordH.setError("Password must be at least 7 characters long");
                    return;
                }
                if (TextUtils.isEmpty(location))
                {
                    EmailH.setError("Location is needed to register.");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused)
                                {
                                    Toast.makeText(regHospital.this, "Verification email has been sent. ", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent" + e.getMessage());
                                }
                            });

                            Toast.makeText(regHospital.this, "New Hospital Account is Created. ", Toast.LENGTH_SHORT).show();
                            hospitalID = fAuth.getCurrentUser().getUid();

                            UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(hospitalID);
                            HashMap userMap = new HashMap();
                            userMap.put("username",fullname);
                            UserRef.updateChildren(userMap);

                            DocumentReference documentReference = fStore.collection("hospitals").document(hospitalID);
                            Map<String,Object> hospital = new HashMap<>();
                            hospital.put("HospitalName",fullname);
                            hospital.put("HospitalEmail",email);
                            hospital.put("HospitalLocation",location);
                            hospital.put("userId",hospitalID);

                           // user.put("isUser","1");

                            documentReference.set(hospital).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused)
                                {
                                    Log.d(TAG, "onSuccess: Hospital profile is created for"+hospitalID);
                                }
                            }).addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), navigationHospital.class));
                            finish();

                        }
                        else
                        {
                            Toast.makeText(regHospital.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        OldAccountH.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), com.example.seniordesign.logHospital.class));
            }
        });
    }
}
