package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register1 extends AppCompatActivity
{
    public static final String TAG = "TAG";

    TextView AppName1;
    EditText MinimumBP, MaximumBP;
    Button RegisterButton1;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);

        AppName1 = findViewById(R.id.appName1);
        MaximumBP = findViewById(R.id.maxbpressure);
        MinimumBP = findViewById(R.id.minbpressure);
        RegisterButton1 = findViewById(R.id.registerButton1);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        RegisterButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                final String maxbpressure = MaximumBP.getText().toString();
                int intValue = Integer.parseInt(maxbpressure);
                final String minbpressure = MinimumBP.getText().toString();
                int intValue1 = Integer.parseInt(minbpressure);

                if (intValue > 120) {
                    MaximumBP.setError("Maximum blood pressure should be less than 120.");
                    return;
                }
                if (intValue1 < 80) {
                    MinimumBP.setError("Minimum blood pressure should be more than 80.");
                    return;
                }

                userID = fAuth.getCurrentUser().getUid();
                DocumentReference documentReference = fStore.collection("users").document(userID);
                Map<String, Object> user = new HashMap<>();
                user.put("MaximumBP", maxbpressure);
                user.put("MinimumBP", minbpressure);

                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: go to next step");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.toString());
                    }
                });
                startActivity(new Intent(getApplicationContext(), register.class));

            }
        });

    }

}


