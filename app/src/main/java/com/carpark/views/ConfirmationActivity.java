package com.carpark.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.carpark.R;
import com.carpark.utils.SharePreference;

public class ConfirmationActivity extends AppCompatActivity {
    private TextView park, address, date_in, date_out, time_in, time_out, vehicle_name, vehicle_number;
    private final String PREFERENCE_FILE_KEY = "location_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        getSupportActionBar().setTitle("Confirmation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        park = findViewById(R.id.tv1);
        address = findViewById(R.id.tv2);
        date_in = findViewById(R.id.tv20);
        date_out = findViewById(R.id.tv21);
        time_in = findViewById(R.id.tv22);
        time_out = findViewById(R.id.tv23);
        vehicle_name = findViewById(R.id.tv25);
        vehicle_number = findViewById(R.id.tv26);

        date_in.setText(SharePreference.getINSTANCE(getApplicationContext()).getINFormattedDay());
        date_out.setText(SharePreference.getINSTANCE(getApplicationContext()).getOutFormattedDay());
        time_in.setText(SharePreference.getINSTANCE(getApplicationContext()).getINFormattedTime());
        time_out.setText(SharePreference.getINSTANCE(getApplicationContext()).getOutFormattedTime());
        vehicle_name.setText(SharePreference.getINSTANCE(getApplicationContext()).getMainVehicleName());
        vehicle_number.setText(SharePreference.getINSTANCE(getApplicationContext()).getMainVehicleNumber());
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String park_name = sharedPref.getString("Park_Name","null");
        String park_address = sharedPref.getString("Park_Address","null");

        park.setText(park_name);
        address.setText(park_address);
    }

    public void confirmBooking(View view){
        showAlert();
    }

    public void editBooking(View view){
        Intent intent = new Intent(ConfirmationActivity.this, ScheduleActivity.class);
        startActivity(intent);
        finish();
    }
    private void showAlert() {
        Button invoice;
        final AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        final View customView = getLayoutInflater().inflate(R.layout.confirmation_dialogue, null);
        invoice = customView.findViewById(R.id.invoice_btn);
        myDialog.setView(customView);
        final AlertDialog dialog = myDialog.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmationActivity.this, InvoiceActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
