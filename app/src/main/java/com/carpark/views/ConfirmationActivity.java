package com.carpark.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carpark.Api.ParkingApi;
import com.carpark.Api.Responses.Park.ParkingSpaceDataResponse;
import com.carpark.Api.RetrofitClient;
import com.carpark.Model.Booking.BookingSchedule;
import com.carpark.Model.Park.UserPackedSpace;
import com.carpark.R;
import com.carpark.utils.SharePreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmationActivity extends BaseActivity {
    private TextView park, address, date_in, date_out, time_in, time_out, vehicle_name, vehicle_number;
    private final String PREFERENCE_FILE_KEY = "location_pref";
    private BookingSchedule bookingSchedule;
    private Button confirm_button;
    private ProgressBar progressBar;
    private int car_park_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        getSupportActionBar().setTitle("Confirmation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookingSchedule = new BookingSchedule();

        park = findViewById(R.id.tv1);
        address = findViewById(R.id.tv2);
        date_in = findViewById(R.id.tv20);
        date_out = findViewById(R.id.tv21);
        time_in = findViewById(R.id.tv22);
        time_out = findViewById(R.id.tv23);
        vehicle_name = findViewById(R.id.tv25);
        vehicle_number = findViewById(R.id.tv26);
        confirm_button = findViewById(R.id.confirm_button);
        progressBar = findViewById(R.id.progressBar4);

        date_in.setText(SharePreference.getINSTANCE(getApplicationContext()).getINFormattedDay());
        date_out.setText(SharePreference.getINSTANCE(getApplicationContext()).getOutFormattedDay());
        time_in.setText(SharePreference.getINSTANCE(getApplicationContext()).getINFormattedTime());
        time_out.setText(SharePreference.getINSTANCE(getApplicationContext()).getOutFormattedTime());
        vehicle_name.setText(SharePreference.getINSTANCE(getApplicationContext()).getMainVehicleName());
        vehicle_number.setText(SharePreference.getINSTANCE(getApplicationContext()).getMainVehicleNumber());
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String park_name = sharedPref.getString("Park_Name","null");
        String park_address = sharedPref.getString("Park_Address","null");
        car_park_id = sharedPref.getInt("id",0);

        park.setText(park_name);
        address.setText(park_address);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmBooking();
            }
        });
    }
    public void confirmBooking(){
        confirm_button.setClickable(false);
        progressBar.setVisibility(View.VISIBLE);
        bookSpace();
    }


    private void bookSpace() {
        String token = SharePreference.getINSTANCE(getApplicationContext()).getAccessToken();

        ParkingApi parkingApi = RetrofitClient.getInstance().create(ParkingApi.class);
        bookingSchedule.setCheckIn(SharePreference.getINSTANCE(this).getCheckIn());
        bookingSchedule.setCheckOut(SharePreference.getINSTANCE(this).getCheckOut());
        bookingSchedule.setVehicleNo(SharePreference.getINSTANCE(getApplicationContext()).getMainVehicleNumber());
        parkingApi.scheduleParkingSpace(token, car_park_id, bookingSchedule).enqueue(new Callback<ParkingSpaceDataResponse<UserPackedSpace>>() {
            @Override
            public void onResponse(Call<ParkingSpaceDataResponse<UserPackedSpace>> call, Response<ParkingSpaceDataResponse<UserPackedSpace>> response) {
                if(response.isSuccessful()){
                    showAlert();
                    progressBar.setVisibility(View.INVISIBLE);

                }else{
                    showToast("Failure " + response.code());
                    confirm_button.setClickable(true);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ParkingSpaceDataResponse<UserPackedSpace>> call, Throwable t) {
                showToast("Please Try again");
                confirm_button.setClickable(true);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

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
        SharePreference.getINSTANCE(this).setDuration("-----");
        SharePreference.getINSTANCE(this).setINFormattedDate("-----");
        SharePreference.getINSTANCE(this).setOutFormattedDate("-----");
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
