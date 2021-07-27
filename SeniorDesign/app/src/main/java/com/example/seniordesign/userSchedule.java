package com.example.seniordesign;

import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class userSchedule extends AppCompatActivity {

    DatabaseReference  requestRef, friendsRef,userRef;
    FirebaseAuth dAuth;
    FirebaseFirestore db;

    TextView name;
    Button backBtn, requestBtn, declineBtn;
    String currentState;
    String senderUserId,receivingUserId,saveCurrentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule);

        receivingUserId = getIntent().getStringExtra("uname");


        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        requestRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        friendsRef = FirebaseDatabase.getInstance().getReference().child("Friends");
        dAuth = FirebaseAuth.getInstance();
        senderUserId = dAuth.getCurrentUser().getUid();



        name = findViewById(R.id.textView18);
        backBtn = findViewById(R.id.buttonListUser);
        requestBtn = findViewById(R.id.requestButton);
        declineBtn = findViewById(R.id.declineButton);
        currentState = "not_friends";

        name.setText(getIntent().getStringExtra("uname".toString()));


        declineBtn.setVisibility(View.INVISIBLE);
        declineBtn.setEnabled(false);

        userRef.child(senderUserId);
        userRef.child(receivingUserId);


        userRef.child(receivingUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Log.d("Inside Maintainenance","MOMO");
                    maintenanceOfButton();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(!String.valueOf(senderUserId).equals(receivingUserId))
        {
            requestBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    requestBtn.setEnabled(false);

                    if(currentState.equals("not_friends"))
                    {
                        sendFriendRequestToAPerson();
                    }
                    if(currentState.equals("request_sent"))
                    {
                        cancelFriendRequest();
                    }
                    if(currentState.equals("request_received"))
                    {
                        acceptFriendRequest();
                    }
                    if(currentState.equals("friends"))
                    {
                        unFriendExistingFriend();
                    }
                }
            });
        }
    }

    private void unFriendExistingFriend()
    {
        friendsRef.child(senderUserId).child(receivingUserId)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            friendsRef.child(receivingUserId).child(senderUserId)
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull  Task<Void> task) {
                                            if(task.isSuccessful()){
                                                requestBtn.setEnabled(true);
                                                currentState = "not_friends";
                                                requestBtn.setText("SEND REQUEST");
                                                declineBtn.setVisibility(View.INVISIBLE);
                                                declineBtn.setEnabled(false);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }


    private void acceptFriendRequest()
    {
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        friendsRef.child(senderUserId).child(receivingUserId).child("date").setValue(saveCurrentDate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                             friendsRef.child(receivingUserId).child(senderUserId).child("date").setValue(saveCurrentDate)
                                     .addOnCompleteListener(new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task <Void> task) {
                                             if(task.isSuccessful())
                                             {
                                                 requestRef.child(senderUserId).child(receivingUserId)
                                                         .removeValue()
                                                         .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                             @Override
                                                             public void onComplete(@NonNull  Task<Void> task) {
                                                                 if(task.isSuccessful())
                                                                 {
                                                                     requestRef.child(receivingUserId).child(senderUserId)
                                                                             .removeValue()
                                                                             .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                 @Override
                                                                                 public void onComplete(@NonNull  Task<Void> task) {
                                                                                     if(task.isSuccessful()){
                                                                                         requestBtn.setEnabled(true);
                                                                                         currentState = "friends";
                                                                                         requestBtn.setText("Unfriend this Person");

                                                                                         declineBtn.setVisibility(View.INVISIBLE);
                                                                                         declineBtn.setEnabled(false);
                                                                                     }
                                                                                 }
                                                                             });
                                                                 }
                                                             }
                                                         });
                                             }
                                         }
                                     });
                        }
                    }
                });


    }

    private void cancelFriendRequest() {
        requestRef.child(senderUserId).child(receivingUserId)
                .removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            requestRef.child(receivingUserId).child(senderUserId)
                                    .removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull  Task<Void> task) {
                                            if(task.isSuccessful()){
                                                requestBtn.setEnabled(true);
                                                currentState = "not_friends";
                                                requestBtn.setText("SEND REQUEST");
                                                declineBtn.setVisibility(View.INVISIBLE);
                                                declineBtn.setEnabled(false);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }



    //checks the request type and changes request button to cancel button if request is sent
    private void maintenanceOfButton()
    {
        Log.d("Rcvng id in mtnancebtn",receivingUserId);
        requestRef.child(senderUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if (snapshot.hasChild(receivingUserId))
                {
                    String request_type = snapshot.child(receivingUserId).child("request_type").getValue().toString();
                    Log.d("REQVvVVVVVVV",request_type);

                    if(request_type.equals("sent"))
                    {

                        Log.d("reqyesttype is","sent");
                        currentState = "request_sent";
                        requestBtn.setText("Cancel Request");
                        declineBtn.setVisibility(View.INVISIBLE);
                        declineBtn.setEnabled(false);
                    }

                    else if (request_type.equals("received"))
                    {
                        Log.d("accepttype is","received");
                        currentState = "request_received";
                        requestBtn.setText("Accept_Friend_Request");
                        declineBtn.setVisibility(View.VISIBLE);
                        declineBtn.setEnabled(true);

                        declineBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cancelFriendRequest();
                            }
                        });
                    }
                }
                else
                {
                    friendsRef.child(senderUserId)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull  DataSnapshot snapshot)
                                {
                                    if( snapshot.hasChild(receivingUserId))
                                    {
                                        currentState = "friends";
                                        requestBtn.setText("Unfriend this person");

                                        declineBtn.setVisibility(View.INVISIBLE);
                                        declineBtn.setEnabled(false);


                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull  DatabaseError error) {

                                }
                            });
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


    }


    private void sendFriendRequestToAPerson() {
        Log.d("HERE","HERE");
        requestRef.child(String.valueOf(senderUserId)).child(receivingUserId)
                .child("request_type").setValue("sent")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull  Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Log.d("ARSENAL","GUNNERS");
                            requestRef.child(receivingUserId).child(senderUserId)
                                    .child("request_type").setValue("received").
                                    addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull  Task<Void> task) {
                                            if(task.isSuccessful()){

                                                Log.d("THERE","THERE");
                                                requestBtn.setEnabled(true);
                                                currentState = "request_sent";
                                                requestBtn.setText("Cancel Request");
                                                declineBtn.setVisibility(View.INVISIBLE);
                                                declineBtn.setEnabled(false);
                                            }
                                        }
                                    });
                        }
                    }
                });
    }



}
