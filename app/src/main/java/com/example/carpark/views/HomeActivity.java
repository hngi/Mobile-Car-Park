package com.example.carpark.views;

import android.os.Bundle;
import android.view.MenuItem;
;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.carpark.MyVehicleFragment;
import com.example.carpark.ParkingHistoryFragment;
import com.example.carpark.R;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends FragmentActivity {

    //widgets
    private DrawerLayout mDrawerLayout;
    private Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        setUpDefaultFragment();
        navigationClickListeners();
    }

    private void initViews() {
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.home_toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setUpDefaultFragment() {
        DefaultFragment defaultFragment = new DefaultFragment();
        setUpFragment(defaultFragment);
    }


    private void navigationClickListeners() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.nav_notification:
                        fragment = null;
                        break;

                    case R.id.nav_parking_history:
                        fragment = new ParkingHistoryFragment();
                        break;

                    case R.id.nav_pay:
                        fragment = new PaymentMethodsFragment();
                        break;

                    case R.id.nav_prom:
                        fragment = new PromotionFragment();
                        break;

                    case R.id.nav_car:
                        fragment = new MyVehicleFragment();
                        break;

                    case R.id.nav_settings:
                        fragment = new SettingsFragment();
                        break;


                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                navigationView.setCheckedItem(item);
                if (fragment != null) {
                    setUpFragment(fragment);
                }
                return true;
            }
        });

    }

    private void setUpFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame, fragment).commit();

    }

}
