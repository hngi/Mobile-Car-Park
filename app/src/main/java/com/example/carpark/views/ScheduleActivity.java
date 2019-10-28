package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpark.R;

public class ScheduleActivity extends AppCompatActivity {
    private TextView changeCheckIn;
    private TextView changeCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        getSupportActionBar().setTitle("Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void next(View view){
        Intent intent = new Intent(ScheduleActivity.this, ConfirmationActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkIn(View view){
        Toast.makeText(getApplicationContext(), "Change CheckIn date", Toast.LENGTH_LONG).show();
    }

    public void checkOut(View view){
        Toast.makeText(getApplicationContext(), "Change CheckOut date", Toast.LENGTH_LONG).show();
    }

    public void addVehicle(View view){
        Intent intent = new Intent(ScheduleActivity.this, DetailsActivity.class);
        startActivity(intent);
    }
}
