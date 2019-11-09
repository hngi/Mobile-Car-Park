package com.carpark.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.carpark.Api.ParkingApi;
import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Api.RetrofitClient;
import com.carpark.Model.User;
import com.carpark.Model.UserProfile;
import com.carpark.R;
import com.carpark.views.homefragments.MyVehicleFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Utibe Etim
 * etim.utibe@gmail.com
 * 23 Oct, 2019
 */
public class ProfileActivity extends BaseActivity {

    private TextInputEditText edtFirstName, edtLastName, edtPhone, edtEmail;
    private Button save_btn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
    }

    private void initView() {
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        save_btn = findViewById(R.id.profile_save_btn);

        User user = getStoredUser();
        if (user != null) {
            edtFirstName.setText(user.getFirstName());
            edtLastName.setText(user.getLastName());
            edtPhone.setText(user.getPhone());
            edtEmail.setText(user.getEmail());
        }

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = edtFirstName.getText().toString().trim();
                String lastName = edtLastName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();

                if (firstName.isEmpty()) {
                    showToast("First name cannot be empty");
                    return;
                }

                if (lastName.isEmpty()) {
                    showToast("Last name cannot be empty");
                    return;
                }

                if (phone.isEmpty()) {
                    showToast("Phone number cannot be empty");
                    return;
                }

                if (email.isEmpty()) {
                    showToast("Email address cannot be empty");
                    return;
                }

                updateProfile(firstName, lastName, phone, email);

            }
        });
    }

    private void updateProfile(String firstName, String lastName, String phone, String email) {
        UserProfile  userProfile = new UserProfile();
        userProfile.setFirstName(firstName);
        userProfile.setLastName(lastName);
        userProfile.setEmail(email);
        userProfile.setPhone(phone);


        String token = getSharePref().getAccessToken();

        showProgressDialog();
        RetrofitClient.getInstance().create(ParkingApi.class).editUserProfile(token, userProfile).enqueue(new Callback<BaseDataResponse<UserProfile>>() {
            @Override
            public void onResponse(@NotNull Call<BaseDataResponse<UserProfile>> call, @NotNull Response<BaseDataResponse<UserProfile>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        if (response.body().getData() != null) {
                            showToast(response.body().getMessage());
                            UserProfile newProfile = response.body().getData();
                            User user = getStoredUser();
                            user.setFirstName(newProfile.getFirstName());
                            user.setLastName(newProfile.getLastName());
                            user.setEmail(newProfile.getEmail());
                            user.setPhone(newProfile.getPhone());
                            setStoredUser(user);
                            finish();
                        } else {
                            showToast(response.body().getMessage());
                        }
                    }
                }else {
                    showToast("Error updating profile");
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(@NotNull Call<BaseDataResponse<UserProfile>> call, @NotNull Throwable t) {
                showToast(t.getMessage()+ " Failure");
                hideProgressDialog();
            }
        });
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating Profile...");
        progressDialog.show();
    }

    private void hideProgressDialog() {
        progressDialog.cancel();
    }
}
