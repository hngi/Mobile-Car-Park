package com.example.carpark.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.carpark.Api.ParkingApi;
import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.RetrofitClient;
import com.example.carpark.Model.Vehicle;
import com.example.carpark.R;
import com.example.carpark.utils.SharePreference;
import com.example.carpark.views.homefragments.MyVehicleFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarDetailsActiviy extends AppCompatActivity {

    Button saveCarDetails;
    TextView carDetails;
    EditText plateNumber, carModel;
    Switch primaryRide;
    private String plateNum,makeModel;
    private boolean main_ride = true;
    private ProgressBar progressBar;
    private String vehicle_id, plate = "new", make;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        getSupportActionBar().setTitle("Add Vehicle"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        vehicle_id = getIntent().getStringExtra("Vehicle_Id");
        plate = getIntent().getStringExtra("plate_number");
        make = getIntent().getStringExtra("make");
        viewsInit();
        getCarDetails(vehicle_id,plate,make);
        if(plate!=null){
            getCarDetails(vehicle_id,plate,make);
        }else{
            saveCarDetails.setText("Save");
            saveCarDetails.setClickable(false);
            getSupportActionBar().setTitle("Add New Vehicle");
            saveCarDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkInputBoxes();
                }
            });
        }
    }

    private void getCarDetails(final String vehicle_id, final String plate, final String make) {
        plateNumber.setText(plate);
        carModel.setText(make);
        saveCarDetails.setText("Update");
        saveCarDetails.setClickable(false);
        getSupportActionBar().setTitle(make);
        saveCarDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCarDetails(vehicle_id, plate, make);
            }
        });

    }

    private void updateCarDetails(String vehicleId, String plate, String make) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        if(plate!=null){
            menu.findItem(R.id.delete).setVisible(true);
        }else{
            menu.findItem(R.id.delete).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                deleteVehicle(vehicle_id);
                Toast.makeText(this, "Vehicle Deleted", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteVehicle(String vehicle_id) {

    }

    // initialising the views
    public void viewsInit(){
        saveCarDetails = (Button) findViewById(R.id.save_car_details);
        primaryRide = (Switch) findViewById(R.id.primary_ride);
        plateNumber = (EditText) findViewById(R.id.car_plate_number);
        carModel = (EditText) findViewById(R.id.car_model);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
    }
    // checking if the input boxes were filled
    public void checkInputBoxes(){
        plateNum = plateNumber.getText().toString();
        makeModel = carModel.getText().toString();
        //checking the input boxes first
        if (plateNum.isEmpty()){
            this.plateNumber.setError("Please fill this field");
        }else if (makeModel.isEmpty()){
            this.carModel.setError("please fill this field");
        }else{
            primaryRide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        main_ride = true;
                    }else{
                        main_ride = false;
                    }
                }
            });
            progressBar.setVisibility(View.VISIBLE);
            saveCar(plateNum,makeModel,main_ride);
        }

    }

    public void saveCar(String plate_number, String make_model,boolean main_ride){
        String token = SharePreference.getINSTANCE(getApplicationContext()).getAccessToken();
        
    }


}
