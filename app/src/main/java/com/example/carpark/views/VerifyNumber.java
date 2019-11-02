package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpark.Api.ParkingApi;
import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.Responses.LoginReg.UserResponse;
import com.example.carpark.Api.RetrofitClient;
import com.example.carpark.R;
import com.google.android.gms.common.api.Api;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyNumber extends AppCompatActivity {

    Button next;
    EditText etPhoneNumer;
    CountryCodePicker tvCountryCode;
    String phoneNumber;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_number);

        ImageButton back = (ImageButton) findViewById(R.id.imageButton2);

        next = findViewById(R.id.next1);
        etPhoneNumer= findViewById(R.id.verify_number);
        tvCountryCode = findViewById(R.id.verify_ccp);

        // this allowsthe passed edittext from getstarted to show
        final String countryCode = getIntent().getStringExtra("countryCode");
        String phone = getIntent().getStringExtra("phoneNumber");
        etPhoneNumer.setText(phone);
        tvCountryCode.setFullNumber(countryCode);
        final String phoneForOTP = etPhoneNumer.getText().toString();
        String countryCodeForOTP = tvCountryCode.getFullNumber();
        final String numberForOTP = countryCodeForOTP+phoneForOTP;

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
               // phoneNumber = etPhoneNumer.getText().toString();

                if (!((phoneForOTP.length() < 10))){

               // SendOtp(numberForOTP);
                    Intent i = new Intent(VerifyNumber.this, EnterOTP.class);
                    startActivity(i);


                }else {
                    Toast.makeText(VerifyNumber.this, "Enter a Valid Number", Toast.LENGTH_SHORT).show();
                }
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerifyNumber.this, GetStarted.class);
                startActivity(i);
               // finish();
            }
        });

    }

    private void SendOtp(final String PhoneForOTP){

        /*RetrofitClient.getInstance().create(ParkingApi.class).sendOTP(PhoneForOTP).enqueue(new Callback<BaseDataResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<UserResponse>> call, Response<BaseDataResponse<UserResponse>> response) {

                if (response.isSuccessful()){
                    Toast.makeText(VerifyNumber.this, "Otp Sent", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(VerifyNumber.this, EnterOTP.class);
                        intent.putExtra("PhoneNumberForOTP", PhoneForOTP);
                        startActivity(intent);

                }else {
                    Toast.makeText(VerifyNumber.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BaseDataResponse<UserResponse>> call, Throwable t) {
                Toast.makeText(VerifyNumber.this, t.getMessage() , Toast.LENGTH_SHORT).show();

            }
        });

*/

    }

}
