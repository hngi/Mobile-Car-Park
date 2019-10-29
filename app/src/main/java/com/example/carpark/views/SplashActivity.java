package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.example.carpark.R;

public class SplashActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Animation fromLeft;
    ImageView car_park_ic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        car_park_ic = findViewById(R.id.splash_img);
        car_park_ic = findViewById(R.id.splash_relative);

        //splash screen animation
//        fromLeft = AnimationUtils.loadAnimation(this, R.anim.fromleft);
//        car_park_ic.setAnimation(fromLeft);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        setContentView(R.layout.activity_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), VerifyNumber.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
