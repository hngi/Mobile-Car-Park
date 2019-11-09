package com.carpark.IgnoreForApiTest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Api.Responses.LoginReg.UserResponse;
import com.carpark.Api.Responses.LoginReg.VerificationResponse;
import com.carpark.Api.Responses.Otp.OTPResponse;
import com.carpark.Model.User;
import com.carpark.R;
import com.carpark.views.BaseActivity;
import com.carpark.views.EnterOTP;
import com.carpark.views.HomeActivity;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 05/11/19
 */

public class StartActivity extends BaseActivity {
    private static final String TAG = "StartActivity";
    private EditText phoneNo;
    private ProgressBar progressBar;
    private String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        phoneNo = findViewById(R.id.number);
        ImageView nextBtn = findViewById(R.id.getSrt_cont_btn);
        progressBar = findViewById(R.id.sendOTPbar);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyNumberIfRegistered();
            }
        });
    }

    private void verifyNumberIfRegistered() {
        showProgressbar();
        phone = phoneNo.getText().toString().trim();
        getParkingApi().verifyPhoneNo(phone).enqueue(new Callback<VerificationResponse>() {
            @Override
            public void onResponse(Call<VerificationResponse> call, Response<VerificationResponse> response) {
                if(response.isSuccessful()){
                    boolean registered = response.body().isRegistered();
                    if(!registered){
                        sendOTP();
                        Log.d(TAG, "Number is not Registered");
                    } else {
                        logInOldUser();
                        Log.d(TAG, "Number is Registered");
                    }
                } else {
                    Log.d(TAG, "SendOTP; Code: " + response.code() + "message; " + response.message());
                    showToast("(sendOTP) Invalid data");
                }
                hideProgressbar();
            }

            @Override
            public void onFailure(Call<VerificationResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                hideProgressbar();
            }
        });
    }

    private void sendOTP() {
        showProgressbar();
        phone = phoneNo.getText().toString().trim();
        getParkingApi().sendOTP(phone).enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                if(response.isSuccessful()){
                    if(!(response.body().isRegistered())){
                        showToast(response.body().getMessage());
                        openOtpActivity();
                    }
                } else {
                    Log.d(TAG, "SendOTP; Code: " + response.code() + "message; " + response.message());
                    showToast("(sendOTP) Invalid data");
                }
                hideProgressbar();
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                hideProgressbar();
            }
        });
    }


    private void logInOldUser(){
        showToast("Should open password Activity First for authentification before going to home");

    }

    private void loginInUser() {
        showProgressbar();
        getParkingApi().loginPhoneNoUser(phone,"12345678").enqueue(new Callback<BaseDataResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<UserResponse>> call, Response<BaseDataResponse<UserResponse>> response) {
                if(response.isSuccessful()){
                    String accessToken = response.body().getData().getAccessToken();
                    int expiresIn = response.body().getData().getExpiresIn();
                    User user = response.body().getData().getUser();

                    getSharePref().setAccesstoken("Bearer " + accessToken);
                    getSharePref().setExpiresIn(expiresIn);
                    setStoredUser(user);

                    //This should go to password activity when api is fixed
                    startActivity(new Intent(StartActivity.this, HomeActivity.class));
                } else {
                    Log.d(TAG, "Login; Code: " + response.code() + " message; " + response.message());
                    showToast("(getLoginAccess) Invalid data");
                }
                hideProgressbar();
            }

            @Override
            public void onFailure(Call<BaseDataResponse<UserResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                hideProgressbar();
            }
        });
    }


    private void openOtpActivity() {
        //THis number should get registered, Its new
        Intent intent = new Intent(this, EnterOTP.class);
        intent.putExtra("PhoneNumberForOTP",phone);
        startActivity(intent);
    }


    private void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }



}
