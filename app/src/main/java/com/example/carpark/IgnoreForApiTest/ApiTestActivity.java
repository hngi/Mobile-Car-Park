package com.example.carpark.IgnoreForApiTest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.Responses.LoginReg.UserResponse;
import com.example.carpark.Model.NewUser;
import com.example.carpark.R;
import com.example.carpark.views.BaseActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);

        Button testBtn=findViewById(R.id.testBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });

    }

    private void registerNewUser() {
        NewUser newUser=new NewUser();
        newUser.setFirstName("Ehma");
        newUser.setLastName("Ugbogo");
        newUser.setOtp("1234");
        newUser.setPhone("08107535626");
        getParkingApi().registerUser(newUser)
                .enqueue(new Callback<BaseDataResponse<UserResponse>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<UserResponse>> call, Response<BaseDataResponse<UserResponse>> response) {
                if(response.isSuccessful()){
                    UserResponse userResponse = response.body().getData();
                    String accessToken = userResponse.getAccessToken();

                    showToast(accessToken);
                    Log.d(TAG, "AccessToken: "+accessToken);
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse<UserResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
