package com.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.carpark.Api.ParkingApi;
import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Api.Responses.LoginReg.UserResponse;
import com.carpark.Api.RetrofitClient;
import com.carpark.Model.NewUser;
import com.carpark.R;
import com.carpark.utils.SharePreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EnterNameActivity extends AppCompatActivity {
    EditText firstName,lastName,  email, password;
    Button btnContinue;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    SharePreference sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        sharedPreferences = getSharedPreferences("API", MODE_PRIVATE);
        sharePref = SharePreference.getINSTANCE(this);

        initView();

        /*
        ImageButton back = (ImageButton) findViewById(R.id.back);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EnterNameActivity.this, EnterOTP.class);
                startActivity(i);
            }
        });*/

    }


    private void initView(){
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        btnContinue = findViewById(R.id.btnContinue);
        progressBar = findViewById(R.id.en_progress_bar);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editContinue();
            }
        });
    }

    public void editContinue(){
        String firstName = this.firstName.getText().toString();
        String lastName = this.lastName.getText().toString();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        String phoneNum = getIntent().getStringExtra("VerifiedPhone");


        if (TextUtils.isEmpty(firstName)) {
            this.firstName.setError("Please enter your first name");
            this.firstName.requestFocus();
        }
        if (TextUtils.isEmpty(lastName)) {
            this.lastName.setError("Please enter your last name");
            this.lastName.requestFocus();
        }
        if (TextUtils.isEmpty(email)) {
            this.email.setError("Please enter your email address");
            this.email.requestFocus();
        }
        if(TextUtils.isEmpty(password)){
            this.password.setError("Please enter password");
            this.password.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            this.email.setError("Please enter a valid email address");
            this.email.requestFocus();
        }

        NewUser newUser = new NewUser();
        newUser.setPhone(phoneNum);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(password);
        newUser.setEmail(email);


        ParkingApi parkingApi = RetrofitClient.getInstance().create(ParkingApi.class);
        progressBar.setVisibility(View.VISIBLE);
        parkingApi.registerUser(newUser).enqueue(new Callback<BaseDataResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<UserResponse>> call, Response<BaseDataResponse<UserResponse>> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);

                    UserResponse userResponse = response.body().getData();
                    String accessToken = userResponse.getAccessToken();
                    sharePref.setAccesstoken(accessToken);
                    Intent intent = new Intent(EnterNameActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    sharePref.setIsUserLoggedIn(true);
                    startActivity(intent);
                    Toast.makeText(EnterNameActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(EnterNameActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse<UserResponse>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(EnterNameActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }
}
