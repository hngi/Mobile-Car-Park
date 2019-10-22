package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;

import com.example.carpark.R;

public class ScheduleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        getSupportActionBar().setTitle("Schedule");
        /*
        toolbar = findViewById(R.id.schedule_toolbar);
        toolbar.setTitle("Schedule");
        toolbar.setTitleTextColor(Color.WHITE);
       // setSupportActionBar(toolbar);*/
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
