package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpark.Api.ParkingApi;
import com.example.carpark.Api.Responses.BaseResponse;
import com.example.carpark.Api.RetrofitClient;
import com.example.carpark.R;
import com.google.android.gms.common.api.Api;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import org.jetbrains.annotations.NotNull;

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
    ProgressBar verifyBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_number);

        ImageButton back = (ImageButton) findViewById(R.id.imageButton2);

        next = findViewById(R.id.next1);
        etPhoneNumer= findViewById(R.id.verify_number);
        tvCountryCode = findViewById(R.id.verify_ccp);
        verifyBar = findViewById(R.id.progressBarVer);

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
                    verifyBar.setVisibility(View.VISIBLE);
                    SendOtp(phoneForOTP);


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

        RetrofitClient.getInstance().create(ParkingApi.class).sendOTP(PhoneForOTP).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse> call, @NotNull Response<BaseResponse> response) {

                if (response.isSuccessful()){
                    Toast.makeText(VerifyNumber.this, "Otp Sent", Toast.LENGTH_SHORT).show();
                    verifyBar.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(VerifyNumber.this, EnterOTP.class);
                        intent.putExtra("PhoneNumberForOTP", PhoneForOTP);
                        startActivity(intent);

                }else {
                    verifyBar.setVisibility(View.INVISIBLE);
                   Toast.makeText(VerifyNumber.this, response.message() + "  Response", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse> call, @NotNull Throwable t) {
                verifyBar.setVisibility(View.INVISIBLE);
                Toast.makeText(VerifyNumber.this, t.getMessage()+ " Failure", Toast.LENGTH_SHORT).show();

            }
        });



    }

}
