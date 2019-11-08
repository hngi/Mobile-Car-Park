package com.carpark.views;

import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.codemybrainsout.onboarder.AhoyOnboarderActivity;
import com.codemybrainsout.onboarder.AhoyOnboarderCard;
import com.carpark.R;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AhoyOnboarderActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        AhoyOnboarderCard onboarderCard1 = new AhoyOnboarderCard("Find parking spaces around your location in no time", null, R.drawable.splash_logo);
        onboarderCard1.setBackgroundColor(R.color.colorPrimary);
        onboarderCard1.setTitleColor(R.color.color_white);
        onboarderCard1.setTitleTextSize(dpToPixels(10, this));
          //setFinishButtonTitle()

        AhoyOnboarderCard onboarderCard2 = new AhoyOnboarderCard("Reserve parking space with no hassle", null, R.drawable.splash_logo);
        onboarderCard2.setBackgroundColor(R.color.colorPrimary);
        onboarderCard2.setTitleColor(R.color.color_white);
        onboarderCard2.setTitleTextSize(dpToPixels(10, this));

        AhoyOnboarderCard onboarderCard3 = new AhoyOnboarderCard("Get notified when Parking lots are nearby", null, R.drawable.splash_logo);
        onboarderCard3.setBackgroundColor(R.color.colorPrimary);
        onboarderCard3.setTitleColor(R.color.color_white);
        onboarderCard3.setTitleTextSize(dpToPixels(10, this));


        List<AhoyOnboarderCard> pages = new ArrayList<>();
        pages.add(onboarderCard1);
        pages.add(onboarderCard2);
        pages.add(onboarderCard3);

        setOnboardPages(pages);

        setColorBackground(R.color.color_white);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }

        //Show/Hide navigation controls
        showNavigationControls(false);

        //Set pager indicator colors
        setInactiveIndicatorColor(R.color.light_grey);
        setActiveIndicatorColor(R.color.colorPrimary);

        //Set finish button text
        setFinishButtonTitle("Get Started");


        //Set the finish button style
        setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.onboarding_finish_btn));
    }

    @Override
    public void onFinishButtonPressed() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("firstTime",false);
        editor.apply();

        intent = new Intent( OnboardingActivity.this, GetStarted.class);
        startActivity(intent);
        finish();
    }
}
