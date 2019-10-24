package com.example.carpark.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.carpark.MyVehicleActivity;
import com.example.carpark.ParkingHistoryActivity;
import com.example.carpark.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

public class Home extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener{

    private GoogleMap mMap;

    //widgets
    private DrawerLayout mDrawerLayout;
    private ImageView mNavMenu;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        navigationView = findViewById(R.id.navigation_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavMenu = findViewById(R.id.nav_menu_icon);

        //setting up onclick listeners
        mNavMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {

            case R.id.nav_notification:

                return true;

            case R.id.nav_parking_history:
                Intent parking_history = new Intent(getApplicationContext(), ParkingHistoryActivity.class);
                startActivity(parking_history);
                return true;

            case R.id.nav_pay:
                Intent payments = new Intent(getApplicationContext(), PaymentMethodsActivity.class);
                startActivity(payments);
                return true;

            case R.id.nav_prom:
                Intent promotions = new Intent(getApplicationContext(), PromotionActivity.class);
                startActivity(promotions);
                return true;

            case R.id.nav_car:
                Intent my_vehicle = new Intent(getApplicationContext(), MyVehicleActivity.class);
                startActivity(my_vehicle);
                return true;

            case R.id.nav_settings:
                fragment = new SettingsFragment();

                if (fragment != null) {
                    //getSupportFragmentManager().beginTransaction().replace(R.id.mai, fragment).commit();
                   // setUpFragment(fragment);
                }
                return true;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }
}
