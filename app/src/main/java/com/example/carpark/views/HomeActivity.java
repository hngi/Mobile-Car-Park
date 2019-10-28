package com.example.carpark.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.carpark.R;
import com.example.carpark.views.homefragments.DefaultFragment;
import com.example.carpark.views.homefragments.MyVehicleFragment;
import com.example.carpark.views.homefragments.ParkingHistoryFragment;
import com.example.carpark.views.homefragments.PaymentMethodsFragment;
import com.example.carpark.views.homefragments.PromotionFragment;
import com.example.carpark.views.homefragments.SettingsFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    //widgets
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    private SearchView searchView;


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
        //searchView = findViewById(R.id.location_search);
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
                String titile = "";
                switch (item.getItemId()) {

                    case R.id.nav_notification:
                        titile = "Notificatins";
                        fragment = null;

                        break;

                    case R.id.nav_parking_history:
                        titile = "Parking History";
                        fragment = new ParkingHistoryFragment();
                        toolbar.setTitle("Parking History");
                        break;

                    case R.id.nav_pay:
                        titile = "Payment Methods";
                        fragment = new PaymentMethodsFragment();
                        toolbar.setTitle("Payment Methods");
                        break;

                    case R.id.nav_prom:
                        titile = "Promotions";
                        fragment = new PromotionFragment();
                        toolbar.setTitle("Promotion");
                        break;

                    case R.id.nav_car:
                        titile = "My Vehicle";
                        fragment = new MyVehicleFragment();
                        toolbar.setTitle("My Vehicle");
                        break;

                    case R.id.nav_settings:
                        titile = "Settings";
                        fragment = new SettingsFragment();
                        toolbar.setTitle("Settings");
                        break;


                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                navigationView.setCheckedItem(item);
                if (fragment != null) {
                    setUpFragment(fragment);
                    enableBackViews(true);
                    toolbar.setTitle(titile);




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

            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
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

            toolbar.setBackgroundColor(getResources().getColor(R.color.white));        }
    }

}
