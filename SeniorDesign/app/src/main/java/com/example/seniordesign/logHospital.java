package com.example.seniordesign;

import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class logHospital extends AppCompatActivity {
    public static final String TAG = "TAG";
    TextView ApplicationTitleH, newUserH, ForgetPasswordH;
    EditText EmailAddressH, Password2H;
    Button LoginButtonH;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_hospital);

        ApplicationTitleH = findViewById(R.id.appTitleH);
        EmailAddressH = findViewById(R.id.eAddressH);
        Password2H = findViewById(R.id.pWordH);
        LoginButtonH = findViewById(R.id.loginButtonH);
        newUserH = findViewById(R.id.newAccountH);
        ForgetPasswordH = findViewById(R.id.forgotPasswordH);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        LoginButtonH.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = EmailAddressH.getText().toString().trim();
                String password = Password2H.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    EmailAddressH.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password2H.setError("Password is required.");
                    return;
                }
                if (password.length() < 8)
                {
                    Password2H.setError("Password must be at least 7 characters long");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>()
                {
                    @Override
                    public void onSuccess(AuthResult authResult)
                    {
                        Toast.makeText(logHospital.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        //checkAccessLevel(authResult.getUser().getUid());
                         startActivity(new Intent(getApplicationContext(), navigationHospital.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.d(TAG, "Login Failed: Email or Password is incorrect.");
                    }
                });
            }

        });

        newUserH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(),regHospital.class));
            }
        });

        ForgetPasswordH.setOnClickListener(new View.OnClickListener()
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
                            public void onSuccess(Void aVoid)
                            {
                                Toast.makeText(logHospital.this, "Check the reset link sent to your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Toast.makeText(logHospital.this, "Error, reset link is not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
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

    /*private void checkAccessLevel(String uid) {
        DocumentReference df = fStore.collection("hospitals").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                Log.d("TAG", "onSuccess: " + documentSnapshot.getData());

                if(documentSnapshot.getString("isAdmin")!=null){
                    startActivity(new Intent(getApplicationContext(),Hospital.class));
                    finish();
                }
                if(documentSnapshot.getString("isUser")!=null){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }
        });
    } */
}