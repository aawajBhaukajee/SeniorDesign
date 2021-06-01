package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity
{
    TextView AppName, RegisterInfo, login;
    EditText FullName, Email, Password, MinBloodPressure, MaxBloodPressure;
    Button RegButton;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AppName = findViewById(R.id.appName);
        RegisterInfo = findViewById(R.id.reg);
        login = findViewById(R.id.noLogin);
        FullName = findViewById(R.id.name);
        Email = findViewById(R.id.emailAddress);
        Password = findViewById(R.id.pword);
        RegButton = findViewById(R.id.Registerbutton);
        MaxBloodPressure = findViewById(R.id.maxbpressure);
        MinBloodPressure = findViewById(R.id.minbpressure);
        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is needed to register.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password is needed to register.");
                    return;
                }

                if (password.length() < 8) {
                    Password.setError("Password must be at least 7 characters long");
                    return;
                }
               /* if(MinBloodPressure<80){
                    MinBloodPressure.setError("Minimum blood pressure should be more than 80.");
                    return;
                }
                if(MaxBloodPressure>120){
                    MaxBloodPressure.setError("Maximum blood pressure should be more than 80.");
                    return;
                }*/
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task  .isSuccessful()) {
                            Toast.makeText(register.this, "New User Created. ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(register.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.example.seniordesign.Login.class));
            }
        });
    }
}
