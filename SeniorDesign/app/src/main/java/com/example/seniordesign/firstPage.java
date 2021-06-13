package com.example.seniordesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class firstPage extends AppCompatActivity {

    Button asUser, asHospital;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        asUser = findViewById(R.id.uRegister);
        asHospital = findViewById(R.id.hRegister);

        asUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), register.class));

            }
        });

        asHospital.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), regHospital.class));

            }
        });



    }
}


