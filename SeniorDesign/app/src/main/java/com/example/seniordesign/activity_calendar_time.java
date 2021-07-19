package com.example.seniordesign;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.TimePicker;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class activity_calendar_time extends AppCompatActivity {

    TimePicker Pick_time;
    Button getTime_btn;
    TextView view_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_time);

        view_time = (TextView) findViewById(R.id.textView_time);
        Pick_time = (TimePicker) findViewById(R.id.time_picker);
        Pick_time.setIs24HourView(false);

        getTime_btn = (Button) findViewById(R.id.button_time);


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
    }
}
