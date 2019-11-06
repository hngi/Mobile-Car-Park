package com.example.carpark.IgnoreForApiTest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.Responses.BaseResponse;
import com.example.carpark.Api.Responses.LoginReg.UserResponse;
import com.example.carpark.Api.Responses.Otp.OTPResponse;
import com.example.carpark.Model.PhoneOtp;
import com.example.carpark.R;
import com.example.carpark.views.BaseActivity;
import com.example.carpark.views.EnterOTP;
import com.example.carpark.views.HomeActivity;

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
        ImageView continueBtn = findViewById(R.id.getSrt_cont_btn);
        progressBar = findViewById(R.id.get_act_progressBar);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyNumberIfRegistered();
            }
        });
    }

    private void verifyNumberIfRegistered() {
        //This Endpoint will be upgraded to verifyNumberIfRegistered later on - Logic will still be the same
        showProgressbar();
        phone = phoneNo.getText().toString().trim();
        getParkingApi().sendOTP(phone).enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                if(response.isSuccessful()){
                    boolean registered = response.body().isRegistered();
                    if(registered){
                        getLoginOtpThenLoginOnSuccess();
                    } else {
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

    private void getLoginOtpThenLoginOnSuccess(){
        getParkingApi().getLoginOTP(phone).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.isSuccessful()){
                    showToast(response.body().getMessage());
                    loginInUser();
                } else {
                    Log.d(TAG, "getLoginOtpThenLoginOnSuccess; Code: " + response.code() + " message; " + response.message());
                    showToast("(VerifyOtp) Invalid data");
                }
                hideProgressbar();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                hideProgressbar();
            }
        });
    }

    private void loginInUser() {
        showProgressbar();
        getParkingApi().getLoginAccess(new PhoneOtp(phone,"1234")).enqueue(new Callback<BaseDataResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<UserResponse>> call, Response<BaseDataResponse<UserResponse>> response) {
                if(response.isSuccessful()){
                    String accessToken = response.body().getData().getAccessToken();
                    int expiresIn = response.body().getData().getExpiresIn();
                    getSharePref().setAccesstoken(accessToken);
                    getSharePref().setExpiresIn(expiresIn);

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
