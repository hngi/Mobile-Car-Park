package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.carpark.R;

public class NotificationActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initViews();
    }

    private void initViews() {
        toolbar = findViewById( R.id.notif_toolbar);
        setSupportActionBar(toolbar);
    }
}
