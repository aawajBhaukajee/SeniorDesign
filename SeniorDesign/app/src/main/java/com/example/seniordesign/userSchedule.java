package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class userSchedule extends AppCompatActivity {
    TextView name;
    Button back, request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_schedule);

        name = findViewById(R.id.textView18);
        back = findViewById(R.id.buttonListUser);
        request = findViewById(R.id.requestButton);

        name.setText(getIntent().getStringExtra("uname".toString()));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AllUsers.class));
            }
        });
    }
}