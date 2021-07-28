package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
public class calendar extends AppCompatActivity {

    private static String d;
    private static String receivingUserId;
    public static String getD(){
        return d;
    }
    public static String getuserId(){
        return receivingUserId;
    }

    CalendarView cal;
    TextView viewDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        cal = (CalendarView)
                findViewById(R.id.calender);
        viewDate = (TextView)
                findViewById(R.id.date_view);

        receivingUserId = getIntent().getStringExtra("uname");

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month,
                                            int day) {
                String Date = (month + 1) + "-" + day + "-" + year;
                viewDate.setText(Date);
            }

        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent Calendar2 = new Intent(getApplicationContext(), calendar2.class);
                d = viewDate.getText().toString().trim();
                startActivity(Calendar2);

            }
        });


    }
}
