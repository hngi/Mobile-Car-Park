package com.example.carpark.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.carpark.R;

public class CarDetailsActiviy extends AppCompatActivity {

    Button saveCarDetails;
    TextView carDetails;
    EditText plateNumber, carModel;
    Switch primaryRide;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        getSupportActionBar().setTitle("Add Vechicle"); // for set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewsInit();

        saveCar();
    }

    // when the back icon on the activity page is clicked
   // public boolean onOptionsItemSelected(MenuItem item) {
   //     switch (item.getItemId()) {
    //        case R.id.action_settings:

//                return true;

//            case R.id.back:
//
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);

//        }
//    }


    // initialising the views
    public void viewsInit(){
        saveCarDetails = (Button) findViewById(R.id.save_car_details);
        primaryRide = (Switch) findViewById(R.id.primary_ride);
        plateNumber = (EditText) findViewById(R.id.car_plate_number);
        carModel = (EditText) findViewById(R.id.car_model);

    }


    // checking if the input boxes were filled
    public void checkInputBoxes(){
        viewsInit();
        final String plateNumber = this.plateNumber.getText().toString();
        String carModel = this.carModel.getText().toString();
        final boolean primary = true;

        //checking the input boxes first
        if (plateNumber.isEmpty()){
            this.plateNumber.setError("Please fill this field");
        }else if (carModel.isEmpty()){
            this.carModel.setError("please fill this field");
        }else{
            // checking the switch button second
            primaryRide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){

                        Toast.makeText(CarDetailsActiviy.this,"Primary",Toast.LENGTH_SHORT);

                    }else{
                        //saving the carDetails to sharedpreferences

                    }

                }
            });
            //saving data with SharedPreferences
            //saving String
            SharedPreferences sp = getSharedPreferences("prefs", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("data1", plateNumber);
            editor.putString("data2",carModel);
            editor.putBoolean("data3",primary);
            editor.commit();

        }

    }

    public void saveCar(){
        saveCarDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewsInit();
                //checkInputBoxes();
                Intent intent = new Intent(CarDetailsActiviy.this, HomeActivity.class);
                startActivity(intent);

            }
        });
    }


}
