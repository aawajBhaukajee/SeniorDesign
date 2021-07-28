package com.example.seniordesign;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class calendar2 extends AppCompatActivity {


    public static final String TAG = "TAG";
    private static String t;

    public static String getT() {
        return t;
    }

    DatabaseReference requestRef, friendsRef,userRef;
    FirebaseAuth dAuth;

    Button requestBtn, declineBtn;
    String currentState;
    String senderUserId,saveCurrentDate;
    private static String receivingUserId;


    TimePicker Pick_time;
    Button getTime_btn;
    TextView view_time;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    String Hname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);

        view_time = (TextView) findViewById(R.id.textView_time);
        Pick_time = (TimePicker) findViewById(R.id.time_picker);
        Pick_time.setIs24HourView(false);

        getTime_btn = (Button) findViewById(R.id.button_time);


        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        receivingUserId = calendar.getuserId().toString();


        userRef = FirebaseDatabase.getInstance().getReference().child("Users");
        requestRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        friendsRef = FirebaseDatabase.getInstance().getReference().child("Friends");
        dAuth = FirebaseAuth.getInstance();
        senderUserId = dAuth.getCurrentUser().getUid();


        requestBtn = findViewById(R.id.requestButton);
        declineBtn = findViewById(R.id.declineButton);
        currentState = "not_friends";

        declineBtn.setVisibility(View.INVISIBLE);
        declineBtn.setEnabled(false);

        Hname = FirestoreAdapterHospital.getHname();

        userRef.child(receivingUserId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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

        getTime_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hrs, min;
                String time_format;

                hrs = Pick_time.getCurrentHour();
                min = Pick_time.getCurrentMinute();

                if (hrs > 12) {
                    time_format = "PM";
                    hrs = hrs - 12;
                } else {
                    time_format = "AM";
                }
                view_time.setText(hrs + ":" + min + " " + time_format);
                t = view_time.getText().toString().trim();
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
                    public void onComplete(@NonNull Task<Void> task) {
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
                                                requestBtn.setText("SEND SCHEDULE REQUEST");
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
                                                                                        requestBtn.setText("REMOVE SCHEDULE");

                                                                                        declineBtn.setVisibility(View.INVISIBLE);
                                                                                        declineBtn.setEnabled(false);


                                                                                        Toast.makeText(calendar2.this, "Scheduled ", Toast.LENGTH_SHORT).show();
                                                                                        final String Time = view_time.getText().toString().trim();



                                                                                        DocumentReference documentReference = fStore.collection("users").document(receivingUserId);

                                                                                        Map<String, Object> update = new HashMap<>();
                                                                                        update.put("Date", calendar.getD());
                                                                                        update.put("Time",Time);
                                                                                        update.put("Schedule Request at:", Hname);

                                                                                        fStore.collection("users").document(userID).set(update, SetOptions.merge());

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
                                                requestBtn.setText("SEND SCHEDULE REQUEST");
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
                        requestBtn.setText("CANCEL REQUEST");
                        declineBtn.setVisibility(View.INVISIBLE);
                        declineBtn.setEnabled(false);
                    }

                    else if (request_type.equals("received"))
                    {
                        Log.d("accepttype is","received");
                        currentState = "request_received";
                        requestBtn.setText("ACCEPT SCHEDULE REQUEST");
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
                                        requestBtn.setText("REMOVE SCHEDULE");

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
                                                requestBtn.setText("CANCEL REQUEST");
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