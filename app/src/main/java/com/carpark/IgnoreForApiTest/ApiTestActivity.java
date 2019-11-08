package com.carpark.IgnoreForApiTest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Api.Responses.BaseResponse;
import com.carpark.Api.Responses.LoginReg.UserResponse;
import com.carpark.Api.Responses.Otp.OTPResponse;
import com.carpark.Model.NewUser;
import com.carpark.Model.PhoneOtp;
import com.carpark.R;
import com.carpark.views.BaseActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 1/11/19
 */


// Please ignore this activity, first its for test purpose. Secondly, its to help show how to make Retrofit
// api Implementation. this class will be deleted later

//To use in yours: ensure to log errors so you see whats happening via logs

public class ApiTestActivity extends BaseActivity {
    private static final String TAG = "ApiTestActivity";
    private ProgressBar progressBar;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        textView = findViewById(R.id.test_textView);
        progressBar = findViewById(R.id.test_progressbar);

    }



    public void RegisterUser(View view) {
        NewUser newUser = new NewUser("1234","08107535626","Ehma","Ugbogo");

        showProgressbar();
        getParkingApi().registerUser(newUser)
                .enqueue(new Callback<BaseDataResponse<UserResponse>>() {
                    @Override
                    public void onResponse(Call<BaseDataResponse<UserResponse>> call, Response<BaseDataResponse<UserResponse>> response) {
                        if (response.isSuccessful()) {
                            UserResponse userResponse = response.body().getData();
                            String accessToken = userResponse.getAccessToken();

                            textView.setText("message; " + response.body().getMessage()+"\nAccessToken: " + accessToken);
                            Log.d(TAG, "Code: " + response.code() + "message; " + response.body().getMessage());
                            Log.d(TAG, "AccessToken: " + accessToken);
                        } else {
                            textView.setText("Success Error " + response.errorBody());
                            textView.append("Code " + response.code());
                            Log.d(TAG, "Code: " + response.code() + "message; " + response.errorBody());
                        }
                        hideProgressbar();
                    }

                    @Override
                    public void onFailure(Call<BaseDataResponse<UserResponse>> call, Throwable t) {
                        textView.setText(String.format("Error; %s", t.getMessage()));
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        hideProgressbar();
                    }
                });
    }

    public void sendOtp(View view) {
        showProgressbar();
        getParkingApi().sendOTP("08026136330").enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    //if(message.equals("OTP verified."))
                    textView.setText(message);
                    Log.d(TAG, "Code: " + response.code() + "message; " + message);
                } else {
                    textView.setText("Success Error " + response.errorBody());
                    textView.append("Code " + response.code());
                    Log.d(TAG, "Code: " + response.code() + "message; " + response.errorBody());
                }
                hideProgressbar();
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                textView.setText(String.format("Error; %s", t.getMessage()));
                Log.d(TAG, "onFailure: " + t.getMessage());
                hideProgressbar();
            }
        });
    }

    public void verifyOtp(View view) {
        showProgressbar();
        PhoneOtp phoneOtp=new PhoneOtp("08026136330","1234");
        getParkingApi().verifyOTP(phoneOtp).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    //if(message.equals("OTP verified."))
                    textView.setText(message);
                    Log.d(TAG, "Code: " + response.code() + "message; " + message);
                } else {
                    textView.setText("Success Error " + response.errorBody().toString());
                    textView.append("Code " + response.code());
                    Log.d(TAG, "Code: " + response.code() + "message; " + response.errorBody());
                }
                hideProgressbar();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                textView.setText(String.format("Error; %s", t.getMessage()));
                Log.d(TAG, "onFailure: " + t.getMessage());
                hideProgressbar();
            }
        });
    }


    private void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }



}
