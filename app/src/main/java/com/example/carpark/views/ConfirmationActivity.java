package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carpark.R;

public class ConfirmationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        getSupportActionBar().setTitle("Confirmation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void confirmBooking(View view){
        Intent intent = new Intent(ConfirmationActivity.this, InvoiceActivity.class);
        startActivity(intent);
    }

    public void editBooking(View view){
        Intent intent = new Intent(ConfirmationActivity.this, ScheduleActivity.class);
        startActivity(intent);
        finish();
    }
}
