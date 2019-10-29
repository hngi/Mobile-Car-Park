package com.example.carpark.views;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.carpark.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ParkLocation extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private static final LatLng Ikeja_G = new LatLng(6.590828, 3.342363);
    private static final LatLng N_Air = new LatLng(6.572047, 3.322867);
    private static final LatLng Ikeja_p = new LatLng(6.595741, 3.337888);


    private Marker mIkeja_g;
    private Marker mN_air;
    private Marker mIkeja_p;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    /** Called when the map is ready. */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Add some markers to the map, and add a data object to each marker.
        mIkeja_g = mMap.addMarker(new MarkerOptions()
                .position(Ikeja_G)
                .title("Ikeja General Hospital, Car Park"));
        mIkeja_g.setTag(0);

        mN_air = mMap.addMarker(new MarkerOptions()
                .position(N_Air)
                .title("New Airport, Car Park"));
        mN_air.setTag(0);

        mIkeja_p = mMap.addMarker(new MarkerOptions()
                .position(Ikeja_p)
                .title("Ikeja, Car Park"));
        mIkeja_p.setTag(0);

        // Set a listener for marker click.
        mMap.setOnMarkerClickListener(this);

        //LatLngBound will cover all your marker on Google Maps
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

//the include method will calculate the min and max bound.
        builder.include(mIkeja_g.getPosition());
        builder.include(mN_air.getPosition());
        builder.include(mIkeja_p.getPosition());

        LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

        mMap.animateCamera(cu);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        return false;
    }

}
