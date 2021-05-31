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
public class Login extends AppCompatActivity {

    TextView title, newAccount;
    EditText Email1, Password1;
    Button RegisterButton;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        title = findViewById(R.id.cash);
        Email1 = findViewById(R.id.eAddress);
        Password1 = findViewById(R.id.Pass);
        RegisterButton = findViewById(R.id.Loginbutton);
        newAccount = findViewById(R.id.newAcc);


        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email1.getText().toString().trim();
                String password = Password1.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Email1.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password1.setError("Password is required.");
                    return;
                }

                if (password.length() < 8) {
                    Password1.setError("Password must be greater then 7 characters");
                    return;
                }

                fAuth= FirebaseAuth.getInstance();

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }

                        else{
                            Toast.makeText(Login.this,"Error !!! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });
    }
}