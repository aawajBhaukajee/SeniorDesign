package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class calendar2 extends AppCompatActivity {


    private static String t;
    public static String getT(){
        return t;
    }

    TimePicker Pick_time;
    Button getTime_btn,sch_btn;
    TextView view_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar2);

        view_time = (TextView) findViewById(R.id.textView_time);
        Pick_time = (TimePicker) findViewById(R.id.time_picker);
        Pick_time.setIs24HourView(false);

        getTime_btn = (Button) findViewById(R.id.button_time);
        sch_btn = findViewById(R.id.button_schedule);


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
            }
        });

        sch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), navigation.class);
                t = view_time.getText().toString().trim();
                Toast.makeText(calendar2.this, "Date and Time Scheduled. ", Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }


        });


    }

}