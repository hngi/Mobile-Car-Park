package com.example.carpark.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}
