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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity
{
    public static final String TAG = "TAG";

    TextView AppName, RegisterTitle, OldAccount;
    EditText FullName, Email, Password, MinimumBP, MaximumBP, Age, Weight, BloodType;
    Button RegisterButton;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AppName = findViewById(R.id.appName);
        RegisterTitle = findViewById(R.id.regTitle);
        OldAccount = findViewById(R.id.hasAccount);
        FullName = findViewById(R.id.name);
        Email = findViewById(R.id.emailAddress);
        Password = findViewById(R.id.password);
        RegisterButton = findViewById(R.id.registerButton);
        Age = findViewById(R.id.age);
        Weight = findViewById(R.id.weight);
        BloodType = findViewById(R.id.bloodtype);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        MaximumBP = findViewById(R.id.maxbpressure);
        MinimumBP = findViewById(R.id.minbpressure);
        /*if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),com.example.seniordesign.register.class));
            finish();
        }
         */
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String fullname = FullName.getText().toString().trim();
                final String email = Email.getText().toString();
                final String password = Password.getText().toString().trim();
                final String btype = BloodType.getText().toString().trim();

                final String maxbpressure = MaximumBP.getText().toString();
                int intValue = Integer.parseInt(maxbpressure);

                final String minbpressure = MinimumBP.getText().toString();
                int intValue1 = Integer.parseInt(minbpressure);

                final String donorage = Age.getText().toString();
                int intValue2 = Integer.parseInt(donorage);

                final String donorweight = Weight.getText().toString();
                int intValue3 = Integer.parseInt(donorweight);

                if (TextUtils.isEmpty(email))
                {
                    Email.setError("Email is needed to register.");
                    return;
                }
                if (TextUtils.isEmpty(btype))
                {
                    Email.setError("Blood type is needed to register.");
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Password.setError("Password is needed to register.");
                    return;
                }
                if (password.length() < 8)
                {
                    Password.setError("Password must be at least 7 characters long");
                    return;
                }

                if (intValue > 120 && intValue < 100) {
                    MaximumBP.setError("Maximum blood pressure should be less than 120 and more than 100.");
                    return;
                }
                if (intValue1 < 80 && intValue1>100) {
                    MinimumBP.setError("Minimum blood pressure should be more than 80 and less than 80");
                    return;
                }

                if (intValue2 >60 || intValue2 < 18) {
                    Age.setError("Your age must be between 18 to 60.");
                    return;
                }

                if (intValue3 < 110 ) {
                    Weight.setError("Your weight must be more than 110 pounds.");
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
                                    Toast.makeText(register.this, "Verification email has been sent. ", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent" + e.getMessage());
                                }
                            });

                            Toast.makeText(register.this, "New User is Created. ", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();

                            user.put("FullName",fullname);
                            user.put("EmailAddress",email);
                            user.put("MaximumBP", maxbpressure);
                            user.put("MinimumBP", minbpressure);
                            user.put("BloodType", btype);
                            user.put("Age", donorage);
                            user.put("Weight", donorweight);
                           // user.put("isUser","1");

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused)
                                {
                                    Log.d(TAG, "onSuccess: user profile is created for"+userID);
                                }
                            }).addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        }
                        else
                        {
                            Toast.makeText(register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        OldAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), com.example.seniordesign.Login.class));
            }
        });
    }


}