package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class timeSchedule extends AppCompatActivity {

    TextView n;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_schedule);

        n=(TextView)findViewById(R.id.textView17);
        b = findViewById(R.id.buttonListHospital);

        n.setText(getIntent().getStringExtra("uname".toString()));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AllHospitals.class));
            }
        });
    }
}