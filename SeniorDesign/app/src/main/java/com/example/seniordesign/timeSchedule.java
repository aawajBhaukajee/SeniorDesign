package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class timeSchedule extends AppCompatActivity {

    FirebaseAuth dAuth;
    FirebaseUser dUser;
    TextView name;
    Button back, request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_schedule);

        String userId = getIntent().getStringExtra("uname");


        dAuth = FirebaseAuth.getInstance();
        dUser = dAuth.getCurrentUser();

        name = findViewById(R.id.textView17);
        back = findViewById(R.id.buttonListHospital);
        request = findViewById(R.id.requestButtonH);


        name.setText(getIntent().getStringExtra("uname".toString()));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AllHospitals.class));
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), calendar.class));
            }
        });

    }

}