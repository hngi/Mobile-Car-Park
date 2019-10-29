package com.example.carpark.views.homefragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpark.R;
import com.example.carpark.views.DetailsActivity;
import com.example.carpark.views.HomeActivity;
import com.example.carpark.views.ScheduleActivity;
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

public class DefaultFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LinearLayout homeSchedule;
    private LinearLayout homeParkingSpace;
    private static final LatLng Ikeja_G = new LatLng(6.590828, 3.342363);
    private static final LatLng N_Air = new LatLng(6.572047, 3.322867);
    private static final LatLng Ikeja_p = new LatLng(6.595741, 3.337888);


    private Marker mIkeja_g;
    private Marker mN_air;
    private Marker mIkeja_p;
    private Button schedule;
    private EditText address_search;

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

        // Add some markers to the map, and add a data object to each marker.
        mIkeja_g = mMap.addMarker(new MarkerOptions()
                .position(Ikeja_G)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_park))
                .title("Ikeja General Hospital, Car Park"));
        mIkeja_g.setTag(0);

        mN_air = mMap.addMarker(new MarkerOptions()
                .position(N_Air)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_park))
                .title("New Airport, Car Park"));
        mN_air.setTag(0);

        mIkeja_p = mMap.addMarker(new MarkerOptions()
                .position(Ikeja_p)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_park))
                .title("Ikeja, Car Park"));
        mIkeja_p.setTag(0);


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
