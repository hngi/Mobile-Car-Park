package com.carpark.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.carpark.R;

public class SplashActivity extends AppCompatActivity {

    Animation fromLeft;
    ImageView car_park_ic;
    Handler handler = new Handler();
    SharedPreferences sharedPreferences;
    Boolean firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        car_park_ic = findViewById(R.id.splash_img);
        //car_park_ic = findViewById(R.id.splash_relative);

        //splash screen animation
        fromLeft = AnimationUtils.loadAnimation(this, R.anim.fromleft);
        car_park_ic.setAnimation(fromLeft);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
        sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE);

        firstTime = sharedPreferences.getBoolean("firstTime",true);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (firstTime) {
                    Intent intent = new Intent(getApplicationContext(), OnboardingActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), GetStarted.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
