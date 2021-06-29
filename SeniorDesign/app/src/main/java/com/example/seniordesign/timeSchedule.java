package com.example.seniordesign;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class timeSchedule extends AppCompatActivity {

    TextView n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_schedule);

        n=(TextView)findViewById(R.id.textView17);

        n.setText(getIntent().getStringExtra("uname".toString()));
    }
}