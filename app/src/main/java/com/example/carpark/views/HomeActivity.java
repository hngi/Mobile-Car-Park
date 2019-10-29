package com.example.carpark.views;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.carpark.R;
import com.example.carpark.views.homefragments.AboutFragment;
import com.example.carpark.views.homefragments.DefaultFragment;
import com.example.carpark.views.homefragments.MyVehicleFragment;
import com.example.carpark.views.homefragments.ParkingHistoryFragment;
import com.example.carpark.views.homefragments.PaymentMethodsFragment;
import com.example.carpark.views.homefragments.PromotionFragment;
import com.example.carpark.views.homefragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

;

public class HomeActivity extends AppCompatActivity {

    //widgets
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private boolean mToolBarNavigationListenerIsRegistered = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        setUpDefaultFragment();
        navigationClickListeners();
    }

    private void initViews() {
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void setUpDefaultFragment() {
        DefaultFragment defaultFragment = new DefaultFragment();
        setUpFragment(defaultFragment);
        toolbar.setTitle(null);
    }



    private void navigationClickListeners() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                String title = "";
                switch (item.getItemId()) {

                    case R.id.nav_notification:
                        title = "Notificatins";
                        fragment = new NotificationFragment();
                        break;

                    case R.id.nav_parking_history:
                        title = "Parking History ";
                        fragment = new ParkingHistoryFragment();
                        break;

                    case R.id.nav_pay:
                        title = "Payment Methods";
                        fragment = new PaymentMethodsFragment();
                        break;

                    case R.id.nav_prom:
                        title = "Promotions";
                        fragment = new PromotionFragment();
                        break;

                    case R.id.nav_car:
                        title = "My Vehicle";
                        fragment = new MyVehicleFragment();
                        break;

                    case R.id.nav_about:
                        title = "About";
                        fragment = new AboutFragment();
                        break;

                    case R.id.nav_settings:
                        title = "Settings";
                        fragment = new SettingsFragment();
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                navigationView.setCheckedItem(item);
                if (fragment != null) {
                    setUpFragment(fragment);
                    enableBackViews(true);
                    toolbar.setTitle(title);

                }
                return true;
            }
        });

    }

    private void setUpFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame, fragment).commit();

    }


    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.home_frame);
        if(f instanceof DefaultFragment ){
            finish();
        }
        else {
            setUpDefaultFragment();
            enableBackViews(false);
        }
    }

    private void enableBackViews(boolean enable) {

        if (enable) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);

            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_action_back);
            upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            toolbar.setTitleTextColor(getResources().getColor(R.color.color_white));
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

            if (!mToolBarNavigationListenerIsRegistered) {
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;

            toolbar.setBackgroundColor(getResources().getColor(R.color.color_white));        }
    }

}
