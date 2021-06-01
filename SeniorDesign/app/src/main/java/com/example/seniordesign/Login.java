package com.example.seniordesign;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity
{
    TextView ApplicationTitle, newUser, ForgetPassword;
    EditText EmailAddress, Password2;
    Button LoginButton;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ApplicationTitle = findViewById(R.id.appTitle);
        EmailAddress = findViewById(R.id.eAddress);
        Password2 = findViewById(R.id.pWord);
        LoginButton = findViewById(R.id.loginButton);
        newUser = findViewById(R.id.newAccount);
        ForgetPassword = findViewById(R.id.forgotPassword);
        fAuth = FirebaseAuth.getInstance();

        LoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = EmailAddress.getText().toString().trim();
                String password = Password2.getText().toString().trim();

                if (TextUtils.isEmpty(email))
                {
                    EmailAddress.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    Password2.setError("Password is required.");
                    return;
                }
                if (password.length() < 8)
                {
                    Password2.setError("Password must be at least 7 characters long");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this, "Error !!! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register.class));
            }
        });

        ForgetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText reset = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reset Password");
                passwordResetDialog.setMessage("Enter email to send reset link");
                passwordResetDialog.setView(reset);
                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int diag)
                    {
                        String mail = reset.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login.this, "Check the reset link sent to your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(Login.this, "Error, reset link is not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int diag)
                    {

                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }
}