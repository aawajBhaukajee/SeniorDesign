package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class timeSchedule extends AppCompatActivity {

    DatabaseReference dref, requestRef, acceptRef;
    FirebaseAuth dAuth;
    FirebaseUser dUser;

    TextView name;
    Button back, request, decline;
    String current = "No Action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_schedule);

        String userId = getIntent().getStringExtra("uname");

        dref = FirebaseDatabase.getInstance().getReference().child("hospitals").child(userId);
        requestRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        acceptRef = FirebaseDatabase.getInstance().getReference().child("Accepted");
        dAuth = FirebaseAuth.getInstance();
        dUser = dAuth.getCurrentUser();

        name=(TextView)findViewById(R.id.textView17);
        back = findViewById(R.id.buttonListHospital);
        request = findViewById(R.id.requestButtonH);
        decline = findViewById(R.id.declineButtonH);

        name.setText(getIntent().getStringExtra("uname".toString()));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AllHospitals.class));
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAction(userId);
            }
        });
        CheckUserExistence(userId);
    }

    private void CheckUserExistence(String userId) {
        acceptRef.child(dUser.getUid()).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    current = "accept";
                    request.setText("Thank You");
                    decline.setText("Remove");
                    decline.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        acceptRef.child(userId).child(dUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    current = "accept";
                    request.setText("Thank You");
                    decline.setText("Remove");
                    decline.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) {
            }
        });

        requestRef.child(dUser.getUid()).child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    if(snapshot.child("status").getValue().toString().equals("pending"))
                    {
                        current = "sent_Pending";
                        request.setText("CANCEL REQUEST");
                        decline.setVisibility(View.GONE);
                    }

                    if(snapshot.child("status").getValue().toString().equals("decline"))
                    {
                        current = "sent_decline";
                        request.setText("CANCEL REQUEST");
                        decline.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        requestRef.child(userId).child(dUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    if(snapshot.child("status").getValue().toString().equals("pending"))
                    {
                        current = "sent_pending";
                        request.setText("ACCEPT REQUEST");
                        decline.setText("DECLINE REQUEST");
                        decline.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(current.equals("No Action"))
        {
            current = "No Action";
            request.setText("SEND REQUEST");
            decline.setVisibility(View.GONE);
        }
    }

    private void PerformAction(String userId) {
        if (current.equals("No Action")) {
            HashMap hashMap = new HashMap();
            hashMap.put("status", "pending");
            requestRef.child(dUser.getUid()).child(userId).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(timeSchedule.this, "You have sent the request", Toast.LENGTH_SHORT).show();
                        decline.setVisibility(View.GONE);
                        current = "sent_Pending";
                        request.setText("CANCEL REQUEST");
                    } else {
                        Toast.makeText(timeSchedule.this, "" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        if (current.equals("sent_Pending") || current.equals("sent_decline")) {
            requestRef.child(dUser.getUid()).child(userId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()) {
                        Toast.makeText(timeSchedule.this, "You have cancelled the request", Toast.LENGTH_SHORT).show();
                        current = "No Action";
                        request.setText("SEND REQUEST");
                        decline.setVisibility(View.GONE);

                    } else {
                        Toast.makeText(timeSchedule.this, "" + task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (current.equals("sent_pending")) {
            requestRef.child(userId).child(dUser.getUid()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        HashMap hashMap = new HashMap();
                        hashMap.put("status", "Accepted");
                        hashMap.put("name", name);

                        acceptRef.child(dUser.getUid()).child(userId).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    acceptRef.child(userId).child(dUser.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            Toast.makeText(timeSchedule.this, "Request is accepted", Toast.LENGTH_SHORT).show();
                                            current = "accept";
                                            request.setText("Thank you");
                                            decline.setText("REMOVE");
                                            decline.setVisibility(View.VISIBLE);
                                        }
                                    });
                                }

                            }
                        });

                    }

                }
            });
        }
        if (current.equals("accept"))
        {

        }
    }
}