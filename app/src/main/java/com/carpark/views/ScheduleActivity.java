package com.carpark.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.carpark.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScheduleActivity extends AppCompatActivity {
    private TextView tvCheckIn;
    private TextView tvCheckOut;
    private TextView tvDuration, park,address;
    FloatingActionButton add_vehicle;
    final Calendar checkInDate = Calendar.getInstance();
    final Calendar checkOutDate = Calendar.getInstance();
    private final String PREFERENCE_FILE_KEY = "location_pref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        getSupportActionBar().setTitle("Booking Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        park = findViewById(R.id.textView2);
        address = findViewById(R.id.textView3);
        tvCheckIn = findViewById(R.id.textView7);
        tvCheckOut = findViewById(R.id.textView10);
        tvDuration = findViewById(R.id.textView13);
        add_vehicle = findViewById(R.id.fb_schedule_add);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String park_name = sharedPref.getString("Park_Name","null");
        String park_address = sharedPref.getString("Park_Address","null");

        park.setText(park_name);
        address.setText(park_address);

        add_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this,CarDetailsActiviy.class);
                startActivity(intent);
            }
        });

    }

    public void next(View view){
        Intent intent = new Intent(ScheduleActivity.this, ConfirmationActivity.class);
        startActivity(intent);
        finish();
    }

    public void checkIn(View view){
        new DatePickerDialog(ScheduleActivity.this , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                checkInDate.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(ScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        checkInDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        checkInDate.set(Calendar.MINUTE, minute);
                        tvCheckIn.setText(getFormattedDate(checkInDate));
                    }
                }, checkInDate.get(Calendar.HOUR_OF_DAY), checkInDate.get(Calendar.MINUTE), false).show();
            }
        }, checkInDate.get(Calendar.YEAR), checkInDate.get(Calendar.MONTH), checkInDate.get(Calendar.DATE)).show();
    }

    public void checkOut(View view){
        if (tvCheckIn.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Kindly set a Check-In time first", Toast.LENGTH_SHORT).show();
            return;
        }
        new DatePickerDialog(ScheduleActivity.this , new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                checkOutDate.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(ScheduleActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        checkOutDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        checkOutDate.set(Calendar.MINUTE, minute);
                        setDuration();
                        tvCheckOut.setText(getFormattedDate(checkOutDate));
                    }
                }, checkOutDate.get(Calendar.HOUR_OF_DAY), checkOutDate.get(Calendar.MINUTE), false).show();
            }
        }, checkOutDate.get(Calendar.YEAR), checkOutDate.get(Calendar.MONTH), checkOutDate.get(Calendar.DATE)).show();
    }

    public void setDuration(){
        long secs = (this.checkOutDate.getTimeInMillis() - this.checkInDate.getTimeInMillis()) / 1000;
        int hours = (int) (secs / 3600);
        secs = secs % 3600;
        int mins = (int) (secs / 60);
        secs = secs % 60;
        tvDuration.setText(String.valueOf(hours) + " hrs " + String.valueOf(mins) + " mins ");
    }

    public String getFormattedDate(Calendar date){
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, HH:mm");
        String strDate = formatter.format(date.getTime());
        return strDate;
    }

    public void addVehicle(View view){
        Intent intent = new Intent(ScheduleActivity.this, DetailsActivity.class);
        startActivity(intent);
    }
}
