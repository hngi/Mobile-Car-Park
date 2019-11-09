package com.carpark.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
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

import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Api.Responses.BaseResponse;
import com.carpark.Api.Responses.LoginReg.UserResponse;
import com.carpark.Api.Responses.Otp.OTPResponse;
import com.carpark.Api.RetrofitClient;

import com.carpark.Model.PhoneOtp;
import com.carpark.R;
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
    String sentOTP, input_otp;
    String phoneNumber, verification_code;
    private static final String TAG = "EnterOTP";
    FirebaseAuth auth;
    private String verificationCode;
    ProgressBar OTPbar;
    TextView resendOTP;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        otp1 = findViewById(R.id.edit_otp1);
        otp2 = findViewById(R.id.edit_otp2);
        otp3 = findViewById(R.id.edit_otp3);
        otp4 = findViewById(R.id.edit_otp4);
        resendOTP = findViewById(R.id.resend_otp);

        receiveNumber = findViewById(R.id.display_number);
        backToVerify = findViewById(R.id.back_verify_num);
        btnToNext = findViewById(R.id.btn_next_otp);
        OTPbar = findViewById(R.id.progress_bar_otp);
        final String phoneNum = getIntent().getStringExtra("PhoneNumberForOTP");

        //receive user phone number from verify number activity
        receiveNumber.setText(getIntent().getStringExtra("PhoneNumber"));

        //arrow back click to return to previous activity
        backToVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backVerify = new Intent(EnterOTP.this, GetStarted.class);
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
                //verifyFBOTP();

                //SigninWithPhone();

            }
        });

        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OTPbar.setVisibility(View.VISIBLE);
                resendOTP.setClickable(false);
                sendOtp(phoneNum);

            }
        });


    }

    public void verifyFBOTP() {
        input_otp = otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString();
        verifyPhoneNumbber(verification_code, input_otp);

    }

    public void verifyPhoneNumbber(String verifyCode, String input_otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifyCode, input_otp);
        signInwithPhone(credential);
    }

    public void signInwithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(), "User has Logged in successfully", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void verifyOTP() {
        if (!(TextUtils.isEmpty(otp1.getText()) && TextUtils.isEmpty(otp2.getText()) && TextUtils.isEmpty(otp3.getText()) && TextUtils.isEmpty(otp4.getText()))) {

            sentOTP = otp1.getText().toString() + otp2.getText().toString() + otp3.getText().toString() + otp4.getText().toString();
            final String phoneNum = getIntent().getStringExtra("PhoneNumberForOTP");
            PhoneOtp phoneOtp = new PhoneOtp(phoneNum, sentOTP);

            getParkingApi().verifyOTP(phoneOtp).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if (response.isSuccessful()) {

                        OTPbar.setVisibility(View.INVISIBLE);
                        String message = response.body().getMessage();
                        Log.d(TAG, "Code: " + response.code() + "message; " + message);
                        Toast.makeText(EnterOTP.this, " Success message: " + message, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EnterOTP.this, EnterNameActivity.class);
                        intent.putExtra("VerifiedPhone", phoneNum);
                        intent.putExtra("OTP", sentOTP);
                        startActivity(intent);
                        //if(message.equals("OTP verified."))

                    } else {
                        OTPbar.setVisibility(View.INVISIBLE);
                        Toast.makeText(EnterOTP.this, " Wrong OTP ", Toast.LENGTH_SHORT).show();
                        //if(message.equals("OTP verified."))


                    }

                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    OTPbar.setVisibility(View.INVISIBLE);
                    Log.d(TAG, "on failure: " + t.getMessage());
                    Toast.makeText(EnterOTP.this, t.getMessage() + " failure message", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            OTPbar.setVisibility(View.INVISIBLE);
            Toast.makeText(EnterOTP.this, "Enter a Valid OTP code", Toast.LENGTH_SHORT).show();
        }

    }

    public void sendOtp(final String phoneForOTP) {
        getParkingApi().sendOTP(phoneForOTP).enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                if (response.isSuccessful()) {
                    OTPbar.setVisibility(View.INVISIBLE);
                        String message = response.body().getMessage();
                        Toast.makeText(EnterOTP.this, message, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Code: " + response.code() + "message; " + message);

                } else {
                    resendOTP.setClickable(true);
                    OTPbar.setVisibility(View.INVISIBLE);
                    assert response.body().getMessage() != null;
                    String message = response.body().getMessage();
                    Toast.makeText(EnterOTP.this, message, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Code: " + response.code() + "message; " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                resendOTP.setClickable(true);
                OTPbar.setVisibility(View.INVISIBLE);
                Toast.makeText(EnterOTP.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }


}
