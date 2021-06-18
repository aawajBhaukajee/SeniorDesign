package com.example.seniordesign;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class schedule extends AppCompatActivity {

    TextView sche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.activity_schedule));

        sche = findViewById(R.id.schedule);

    }
}