package com.example.carpark.views.homefragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.carpark.Model.map.GetNearbyPlacesData;
import com.example.carpark.R;
import com.example.carpark.views.DetailsActivity;
import com.example.carpark.views.MapsActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.facebook.GraphRequest.TAG;

public class DefaultFragment extends Fragment implements OnMapReadyCallback {



    private GoogleMap mMap;

    private LinearLayout homeSchedule;

    private LinearLayout homeParkingSpace;



    public DefaultFragment() {

        // Required empty public constructor

    }



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.content_home, container, false);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);



        setLayoutOnClickListers(root);



        Toast.makeText(getActivity(), "Please click on schedule to test other activities", Toast.LENGTH_LONG).show();



        return root;

    }



    @Override

    public void onMapReady(GoogleMap googleMap) {



        mMap = googleMap;



        // Add a marker in Sydney and move the camera

        LatLng sydney = new LatLng(6.605874, 3.349149);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Ikeja"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



    }



    private void setLayoutOnClickListers(View view){

        homeParkingSpace = view.findViewById(R.id.home_parking_space);

        homeSchedule = view.findViewById(R.id.home_schedule);



        homeParkingSpace.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Toast.makeText(getActivity(), "My Parking Space Clicked", Toast.LENGTH_LONG).show();

            }

        });



        homeSchedule.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), DetailsActivity.class);

                startActivity(intent);

            }

        });



    }



}