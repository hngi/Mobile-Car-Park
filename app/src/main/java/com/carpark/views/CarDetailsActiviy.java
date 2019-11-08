package com.carpark.views;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.carpark.Api.ParkingApi;
import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Api.RetrofitClient;
import com.carpark.Model.Vehicle;
import com.carpark.R;
import com.carpark.utils.SharePreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarDetailsActiviy extends BaseActivity {

    Button saveCarDetails;
    TextView carDetails;
    EditText plateNum, carModel;
    Switch primaryRide;

    private String plateNumber, makeModel;
    private boolean mainRide;

    private ProgressBar progressBar;
    private ProgressDialog progressDialog;

    private String plate, make;
    private int vehicle_id;
    private String token;

    private TextView plateNumberView;


    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        getSupportActionBar().setTitle("Add Vehicle"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        plateNum = findViewById(R.id.car_plate_number);
        carModel = findViewById(R.id.car_model);
        primaryRide = findViewById(R.id.primary_ride);
        saveCarDetails = findViewById(R.id.save_car_details);
        token = SharePreference.getINSTANCE(getApplicationContext()).getAccessToken();

        if (primaryRide.isActivated()) {
            mainRide = true;
        } else {
            mainRide = false;
        }

        plateNumber = plateNum.getText().toString();
        makeModel = carModel.getText().toString();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Adding vehicle");

        saveCarDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plateNumber != "" && makeModel != "") {


                    addNewVehicle(plateNumber, makeModel, mainRide);
                    progressDialog.show();

                }

            }
        });

    }

    public void addNewVehicle(final String plateNumber, final String makeModel, final boolean mainRide) {

        plateNumberView = display;

        final ParkingApi parkingApi = RetrofitClient.getInstance().create(ParkingApi.class);
        parkingApi.addNewVehicle(token, plateNumber, makeModel, mainRide).enqueue(new Callback<BaseDataResponse<Vehicle>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<Vehicle>> call, Response<BaseDataResponse<Vehicle>> response) {

                if (!response.isSuccessful()) {
                    Log.e("Response code", String.valueOf(response.code()));}

                Toast.makeText(CarDetailsActiviy.this, "New Vehicle Added successfully" + response.body(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

                }


            @Override
            public void onFailure(Call<BaseDataResponse<Vehicle>> call, Throwable t) {
                Toast.makeText(CarDetailsActiviy.this, "error: " + t, Toast.LENGTH_SHORT).show();

            }
        });

//        getParkingApi().addNewVehicle(token, plateNumber, makeModel, main_ride).enqueue(new Callback<BaseDataResponse<Vehicle>>() {
//            @Override
//            public void onResponse(Call<BaseDataResponse<Vehicle>> call, Response<BaseDataResponse<Vehicle>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<BaseDataResponse<Vehicle>> call, Throwable t) {
//
//            }
//        });

    }

//    private void updateCarDetails(int vehicleId, String plate, String make) {
//        getParkingApi().editVehicle(token, vehicleId, plate, make).enqueue(new Callback<BaseDataResponse<Vehicle>>() {
//            @Override
//            public void onResponse(Call<BaseDataResponse<Vehicle>> call, Response<BaseDataResponse<Vehicle>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<BaseDataResponse<Vehicle>> call, Throwable t) {
//
//            }
//        });
//    }

//    private void name(){
//        Vehicle vehicle = new Vehicle(token,plateNumber, makeModel, mainRide);
//        Call<BaseDataResponse<Vehicle>> call = ParkingApi.addNewVehicle(vehicle);
//        call.enqueue(new Callback<BaseDataResponse<Vehicle>>() {
//            @Override
//            public void onResponse(Call<BaseDataResponse<Vehicle>> call, Response<BaseDataResponse<Vehicle>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<BaseDataResponse<Vehicle>> call, Throwable t) {
//
//            }
//        });
//    }
//


 }
