package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.carpark.Api.ParkingApi;
import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.Responses.LoginReg.UserResponse;
import com.example.carpark.Api.RetrofitClient;
import com.example.carpark.Model.NewUser;
import com.example.carpark.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnterNameActivity extends AppCompatActivity {
    EditText firstName,lastName;
    Button btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        firstName = findViewById(R.id.firstName);
        //ImageButton back = (ImageButton) findViewById(R.id.back);
        lastName = findViewById(R.id.lastName);
        btnContinue = findViewById(R.id.btnContinue);

        //Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);


        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editContinue();
            }
        });

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(EnterNameActivity.this, EnterOTP.class);
//                startActivity(i);


//            }
//        });

    }



    public void editContinue(){
        String firstName = this.firstName.getText().toString();
        String lastName = this.lastName.getText().toString();
        String otp_create = getIntent().getStringExtra("OTP");
        String  phoneNumber = getIntent().getStringExtra("phone");


        if (TextUtils.isEmpty(firstName)) {
            this.firstName.setError("Please enter your first name");
            this.firstName.requestFocus();
        }

        if(TextUtils.isEmpty(lastName)){
            this.lastName.setError("Please enter your last name");
            this.lastName.requestFocus();
        }

        NewUser newUser = new NewUser();
        newUser.setOtp("1234");
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPhone(phoneNumber);

       /* RetrofitClient.getInstance().create(ParkingApi.class).registerUser(newUser).enqueue(new Callback<BaseDataResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<UserResponse>> call, Response<BaseDataResponse<UserResponse>> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(EnterNameActivity.this, HomeActivity.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse<UserResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/
       Intent intent = new Intent(EnterNameActivity.this, HomeActivity.class);
       startActivity(intent);
    }
}
