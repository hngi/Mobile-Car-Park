package com.example.carpark.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carpark.Api.ParkingApi;
import com.example.carpark.Model.Vehicle;
import com.example.carpark.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarDetailsActiviy extends AppCompatActivity {
//
//    Button saveCarDetails;
//    TextView carDetails;
//    EditText plateNumber, carModel;
//    Switch primaryRide;
//    private String plateNum,makeModel;
//    private boolean main_ride = true;
//    private ProgressBar progressBar;
//    private String vehicle_id, plate = "new", make;

    private ParkingApi parkingApi;
    private EditText plateNumber, makeModel;
    private String plateNumberView, makeModelView;
    private CheckBox main_ride;
    private Button save;

    private boolean primaryRide;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        getSupportActionBar().setTitle("Add Vehicle"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        plateNumber = findViewById(R.id.car_plate_number);
        plateNumberView = plateNumber.getText().toString();

        makeModel = findViewById(R.id.car_model);
        makeModelView = makeModel.getText().toString();

        main_ride = findViewById(R.id.primary_ride);
            if (main_ride.isChecked()){
                primaryRide = true;
            }

        progressDialog = new ProgressDialog(this);
        progressDialog .setMessage("Adding Vehicle...");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hng-car-park-api.herokuapp.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        parkingApi = retrofit.create(ParkingApi.class);


        save = findViewById(R.id.save_car_details);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                  addNewVehicle();
            }

        });

    }

    private void addNewVehicle() {
        final Vehicle vehicle = new Vehicle(plateNumberView, makeModelView, primaryRide);

        Call<Vehicle> call = parkingApi.addNewVehicle(vehicle);
            call.enqueue(new Callback<Vehicle>() {
                @Override
                public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(CarDetailsActiviy.this, "New Vehicle Added successfully" + vehicle, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);

                    }

                    Vehicle postNewVehicle = response.body();
                    plateNumberView = postNewVehicle.getPlateNumber();
                    makeModelView = postNewVehicle.getMakeModel();
                }

                @Override
                public void onFailure(Call<Vehicle> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error " + t, Toast.LENGTH_SHORT).show();

                }
            });

        }



}




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_car_details);
//        getSupportActionBar().setTitle("Add Vehicle"); // for set actionbar title
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        vehicle_id = getIntent().getStringExtra("Vehicle_Id");
//        plate = getIntent().getStringExtra("plate_number");
//        make = getIntent().getStringExtra("make");
//        viewsInit();
//        getCarDetails(vehicle_id,plate,make);
//        if(plate!=null){
//            getCarDetails(vehicle_id,plate,make);
//        }else{
//            saveCarDetails.setText("Save");
//            saveCarDetails.setClickable(false);
//            getSupportActionBar().setTitle("Add New Vehicle");
//            saveCarDetails.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    checkInputBoxes();
//                }
//            });
//        }
//    }
//
//    private void getCarDetails(String vehicleId, String plate, String make) {
//        plateNumber.setText(plate);
//        carModel.setText(make);
//        saveCarDetails.setText("Update");
//        saveCarDetails.setClickable(false);
//        getSupportActionBar().setTitle(make);
//    }
//
//    // initialising the views
//    public void viewsInit(){
//        saveCarDetails = (Button) findViewById(R.id.save_car_details);
//        primaryRide = (Switch) findViewById(R.id.primary_ride);
//        plateNumber = (EditText) findViewById(R.id.car_plate_number);
//        carModel = (EditText) findViewById(R.id.car_model);
//        progressBar = findViewById(R.id.progressBar2);
//        progressBar.setVisibility(View.INVISIBLE);
//    }
//    // checking if the input boxes were filled
//    public void checkInputBoxes(){
//        plateNum = plateNumber.getText().toString();
//        makeModel = carModel.getText().toString();
//        //checking the input boxes first
//        if (plateNum.isEmpty()){
//            this.plateNumber.setError("Please fill this field");
//        }else if (makeModel.isEmpty()){
//            this.carModel.setError("please fill this field");
//        }else{
//            primaryRide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked){
//                        main_ride = true;
//                    }else{
//                        main_ride = false;
//                    }
//                }
//            });
//            progressBar.setVisibility(View.VISIBLE);
//            saveCar(plateNum,makeModel,main_ride);
//        }
//
//    }
//
//    public void saveCar(String plate_number, String make_model,boolean main_ride){
//        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9obmctY2FyLXBhcmstYXBpLmhlcm9rdWFwcC5jb21cL2FwaVwvdjFcL2F1dGhcL3JlZ2lzdGVyXC91c2VyIiwiaWF0IjoxNTcyODc4NDc0LCJleHAiOjE1NzI5ODY0NzQsIm5iZiI6MTU3Mjg3ODQ3NCwianRpIjoidEp4SGJ0OGo1MXFoM25MSSIsInN1YiI6MTIsInBydiI6Ijg3ZTBhZjFlZjlmZDE1ODEyZmRlYzk3MTUzYTE0ZTBiMDQ3NTQ2YWEifQ.vLYVZOEHCk1K79BKzwF2GjdhrTsdgIlfgB3zU6jWEBE";
//
//    }
//
//
//}
