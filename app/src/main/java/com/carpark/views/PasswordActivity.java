package com.carpark.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Api.Responses.LoginReg.UserResponse;
import com.carpark.IgnoreForApiTest.StartActivity;
import com.carpark.Model.User;
import com.carpark.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 07/11/19
 */

public class PasswordActivity extends BaseActivity {
    private static final String TAG = "PasswordActivity";
    public static final String phoneForLoginKEY = "PhoneForLogin";
    private String phoneNo;
    private EditText passwordEditText;
    private String password;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        setup();

    }

    private void setup() {
        Button loginBtn=findViewById(R.id.password_activity_btn);
        passwordEditText = findViewById(R.id.password_activity_Password);
        progressBar = findViewById(R.id.password_activity_progressbar);

        if (getIntent() != null) {
            phoneNo = getIntent().getStringExtra(phoneForLoginKEY);
        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPhoneUser();
            }
        });
    }

    private boolean verifyPassword(){
        showProgressbar();
        password = passwordEditText.getText().toString().trim();
        if(TextUtils.isEmpty(password)){
            passwordEditText.setError("Field is cannot be empty");
            return false;
        }

        if(password.length()<8){
            passwordEditText.setError("Password should be 8 characters minimum");
            return false;
        }
        return true;
    }

    private void loginPhoneUser() {
        if(!verifyPassword()){
            hideProgressbar();
            return;
        }
        getParkingApi().loginPhoneNoUser(phoneNo,password).enqueue(new Callback<BaseDataResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<UserResponse>> call, Response<BaseDataResponse<UserResponse>> response) {
                if(response.isSuccessful()){
                    String accessToken = response.body().getData().getAccessToken();
                    int expiresIn = response.body().getData().getExpiresIn();
                    User user = response.body().getData().getUser();

                    getSharePref().setAccesstoken(accessToken);
                    getSharePref().setExpiresIn(expiresIn);
                    getSharePref().setIsUserLoggedIn(true);
                    setStoredUser(user);
                    Intent i = new Intent(PasswordActivity.this, HomeActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivity(i);
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


    private void hideProgressbar() {
        progressBar.setVisibility(View.GONE);
    }

    private void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }



}
