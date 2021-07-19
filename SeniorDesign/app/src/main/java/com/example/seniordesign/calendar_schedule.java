package com.example.seniordesign;


   import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Button;

public class calendar_schedule extends AppCompatActivity{
        CalendarView cal;
        TextView viewDate;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calender_schedule);

            cal = (CalendarView)
                    findViewById(R.id.calender);
            viewDate = (TextView)
                    findViewById(R.id.date_view);

            cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange( CalendarView view, int year, int month,
                                                 int day) {
                    String Date = (month + 1) + "-" + day + "-" + year;
                    viewDate.setText(Date);
                }

            });

            Button button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent activity_calender_time = new Intent(getApplicationContext(), activity_calendar_time.class);
                    startActivity(activity_calender_time);
                }
            });


        }
    }

