package com.example.carpark.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.carpark.Api.ParkingApi;
import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.RetrofitClient;
import com.example.carpark.Model.Vehicle;
import com.example.carpark.R;
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

    private void getCarDetails(String vehicleId, String plate, String make) {
        plateNumber.setText(plate);
        carModel.setText(make);
        saveCarDetails.setText("Update");
        saveCarDetails.setClickable(false);
        getSupportActionBar().setTitle(make);
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
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9obmctY2FyLXBhcmstYXBpLmhlcm9rdWFwcC5jb21cL2FwaVwvdjFcL2F1dGhcL3JlZ2lzdGVyXC91c2VyIiwiaWF0IjoxNTcyODc4NDc0LCJleHAiOjE1NzI5ODY0NzQsIm5iZiI6MTU3Mjg3ODQ3NCwianRpIjoidEp4SGJ0OGo1MXFoM25MSSIsInN1YiI6MTIsInBydiI6Ijg3ZTBhZjFlZjlmZDE1ODEyZmRlYzk3MTUzYTE0ZTBiMDQ3NTQ2YWEifQ.vLYVZOEHCk1K79BKzwF2GjdhrTsdgIlfgB3zU6jWEBE";
        ParkingApi parkingApi = RetrofitClient.getInstance().create(ParkingApi.class);
        parkingApi.addNewVehicle(token, plate_number,make_model,main_ride).enqueue(new Callback<BaseDataResponse<Vehicle>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<Vehicle>> call, Response<BaseDataResponse<Vehicle>> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),"Successfully added", Toast.LENGTH_LONG);
                    Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(i);
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(),response.code(), Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse<Vehicle>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(),t.getMessage()+"Faill", Toast.LENGTH_LONG);

            }
        });
    }


}
