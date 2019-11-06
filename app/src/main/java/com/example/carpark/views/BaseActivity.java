package com.example.carpark.views;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carpark.Api.ParkingApi;
import com.example.carpark.Api.RetrofitClient;
import com.example.carpark.Model.User;
import com.example.carpark.utils.Commons;
import com.example.carpark.utils.SharePreference;


public class BaseActivity extends AppCompatActivity {
    private SharePreference sharePref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharePref=SharePreference.getINSTANCE(this);
    }

    public SharePreference getSharePref() {
        return sharePref;
    }

    protected void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    public ParkingApi getParkingApi(){
        return RetrofitClient.getInstance().create(ParkingApi.class);
    }

    public void setStoredUser(User user) {
        Commons.setUser(user);
    }

    public User getStoredUser() {
        return Commons.getUser();
    }


}
