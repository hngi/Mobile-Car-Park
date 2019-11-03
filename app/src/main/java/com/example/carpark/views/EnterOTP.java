package com.example.carpark.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.Responses.BaseResponse;
import com.example.carpark.Api.Responses.LoginReg.UserResponse;
import com.example.carpark.Api.RetrofitClient;
import com.example.carpark.Model.PhoneOtp;
import com.example.carpark.R;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterOTP extends BaseActivity {

    EditText otp1, otp2, otp3, otp4;
    TextView receiveNumber;
    ImageView backToVerify;
    Button btnToNext;
    String sentOTP;

    FirebaseAuth auth;
    private String verificationCode;
    ProgressBar OTPbar;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        otp1 = findViewById(R.id.edit_otp1);
        otp2 = findViewById(R.id.edit_otp2);
        otp3 = findViewById(R.id.edit_otp3);
        otp4 = findViewById(R.id.edit_otp4);

        receiveNumber = findViewById(R.id.display_number);
        backToVerify = findViewById(R.id.back_verify_num);
        btnToNext = findViewById(R.id.btn_next_otp);
        OTPbar = findViewById(R.id.progressBarOtp);

        //receive user phone number from verify number activity
        receiveNumber.setText(getIntent().getStringExtra("PhoneNumber"));

        //arrow back click to return to previous activity
        backToVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backVerify = new Intent(EnterOTP.this, VerifyNumber.class);
                startActivity(backVerify);
            }
        });

        //TextWatcher to change focus after each OTP number is entered
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp2.requestFocus();
                } else if (s.length() == 0) {
                    otp1.clearFocus();
                }
            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp3.requestFocus();
                } else if (s.length() == 0) {
                    otp1.requestFocus();
                }
            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp4.requestFocus();
                } else if (s.length() == 0) {
                    otp2.requestFocus();
                }
            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp4.clearFocus();
                } else if (s.length() == 0) {
                    otp3.requestFocus();
                }

            }
        });

        //next button intent
        btnToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OTPbar.setVisibility(View.VISIBLE);
                verifyOTP();

                //SigninWithPhone();

            }
        });

        // startFirebaseLogin();
        // PhoneAuthProvider.getInstance().verifyPhoneNumber(getIntent().getStringExtra("PhoneNumber"), 60, TimeUnit.SECONDS, EnterOTP.this, mCallback);
    }

    private void verifyOTP() {
        if (!(TextUtils.isEmpty(otp1.getText()) && TextUtils.isEmpty(otp2.getText()) && TextUtils.isEmpty(otp3.getText()) && TextUtils.isEmpty(otp4.getText()))) {

            sentOTP = otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString();
            final String phoneNum = getIntent().getStringExtra("PhoneNumberForOTP");
            PhoneOtp phoneOtp = new PhoneOtp();
            phoneOtp.setPhone(phoneNum);
            phoneOtp.setOtp(sentOTP);


            getParkingApi().verifyOTP(phoneOtp).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.isSuccessful()) {

                        if (!sentOTP.equals("1234")) {
                            OTPbar.setVisibility(View.INVISIBLE);
                            Toast.makeText(EnterOTP.this, "Wrong OTP, use 1234 for now", Toast.LENGTH_SHORT).show();
                        } else {
                            OTPbar.setVisibility(View.INVISIBLE);
                            Toast.makeText(EnterOTP.this, "Welcome! " + response.message() , Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EnterOTP.this, EnterNameActivity.class);
                            intent.putExtra("VerifiedPhone", phoneNum);
                            startActivity(intent);
                        }
                    }

                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    OTPbar.setVisibility(View.INVISIBLE);
                    Toast.makeText(EnterOTP.this, t.getMessage() + " Failure", Toast.LENGTH_SHORT).show();

                }

            });


        } else {
            OTPbar.setVisibility(View.INVISIBLE);
            Toast.makeText(EnterOTP.this, "Enter a Valid OTP code", Toast.LENGTH_SHORT).show();
        }

    } /*

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(EnterOTP.this, EnterNameActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(EnterOTP.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
/*
    private void startFirebaseLogin() {

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(EnterOTP.this,"verification completed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(EnterOTP.this,"verification fialed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(EnterOTP.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
        PhoneAuthProvider.getInstance().verifyPhoneNumber(getIntent().getStringExtra("PhoneNumber"), 60, TimeUnit.SECONDS, EnterOTP.this, mCallback);
    }*/
}
