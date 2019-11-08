package com.carpark.views.homefragments;


import android.Manifest;

import android.content.Intent;

import android.content.pm.PackageManager;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.Build;

import android.os.Bundle;



import androidx.core.app.ActivityCompat;

import androidx.core.content.ContextCompat;

import androidx.fragment.app.Fragment;



import android.util.Log;

import android.view.KeyEvent;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;



import com.carpark.Model.map.GetNearbyPlacesData;

import com.carpark.R;

import com.carpark.views.DetailsActivity;

import com.carpark.views.MapsActivity;
import com.carpark.views.MyParkingSpace;
import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.GoogleApiAvailability;

import com.google.android.gms.common.api.GoogleApiClient;

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

import java.io.IOException;
import java.util.List;


public class DefaultFragment extends Fragment implements OnMapReadyCallback,

        GoogleApiClient.ConnectionCallbacks,

        GoogleApiClient.OnConnectionFailedListener,

        GoogleMap.OnMarkerClickListener,

        LocationListener {



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

    private EditText input_search;

    private GoogleApiClient mGoogleApiClient;

    double latitude;

    double longitude;

    private int PROXIMITY_RADIUS = 10000;

    Location mLastLocation;

    Marker mCurrLocationMarker;

    LocationRequest mLocationRequest;

    View root;



    public DefaultFragment() {

        // Required empty public constructor

    }



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.content_home, container, false);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            checkLocationPermission();

        }



        //Check if Google Play Services Available or not

        if (!CheckGooglePlayServices()) {

            Log.d("onCreate", "Finishing test case since Google Play Services are not available");

        }

        else {

            Log.d("onCreate","Google Play Services available.");

        }



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);



        setLayoutOnClickListers(root);
        

       // ImageView imageView = root.findViewById(R.id.ic_magnify);
       /* imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearch(view);
            }
        });*/

        input_search = root.findViewById(R.id.input_search);
        input_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    onSearch();
                    return true;
                }
                return false;
            }
        });




        return root;

    }

    private boolean CheckGooglePlayServices() {

        GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();

        int result = googleAPI.isGooglePlayServicesAvailable(getContext());

        if(result != ConnectionResult.SUCCESS) {

            if(googleAPI.isUserResolvableError(result)) {

                googleAPI.getErrorDialog(getActivity(), result,

                        0).show();

            }

            return false;

        }

        return true;

    }







    /** Called when the map is ready. */

    @Override

    public void onMapReady(GoogleMap map) {

        mMap = map;

        //Initialize Google Play Services

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(getContext(),

                    Manifest.permission.ACCESS_FINE_LOCATION)

                    == PackageManager.PERMISSION_GRANTED) {

                buildGoogleApiClient();

                mMap.setMyLocationEnabled(true);

            }

        }

        else {

            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);

        }

        mMap.clear();





        // Add some markers to the map, and add a data object to each marker.

        mIkeja_g = mMap.addMarker(new MarkerOptions()

                .position(Ikeja_G)

                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon))

                .title("Ikeja General Hospital, Car Park"));

        mIkeja_g.setTag(0);



        mN_air = mMap.addMarker(new MarkerOptions()

                .position(N_Air)

                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon))

                .title("New Airport, Car Park"));

        mN_air.setTag(0);



        mIkeja_p = mMap.addMarker(new MarkerOptions()

                .position(Ikeja_p)

                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon))

                .title("Ikeja, Car Park"));

        mIkeja_p.setTag(0);



        //LatLngBound will cover all your marker on Google Maps

        LatLngBounds.Builder builder = new LatLngBounds.Builder();



//the include method will calculate the min and max bound.

        builder.include(mIkeja_g.getPosition());

        builder.include(mN_air.getPosition());

        builder.include(mIkeja_p.getPosition());



   //     LatLngBounds bounds = builder.build();



     //   int width = getResources().getDisplayMetrics().widthPixels;

       // int height = getResources().getDisplayMetrics().heightPixels;

        //int padding = (int) (width * 0.10); // offset from edges of the map 10% of screen



        //CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);



      //  mMap.animateCamera(cu);



    }

    protected synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())

                .addConnectionCallbacks(this)

                .addOnConnectionFailedListener(this)

                .addApi(LocationServices.API)

                .build();

        mGoogleApiClient.connect();

    }

    public void onSearch(){

            String location = input_search.getText().toString();
             List<Address> addressList = null;

            if(location != null || !location.equals("")){
                Geocoder geocoder = new Geocoder(getContext());

                try {
                     addressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
               try {

                       Address address = addressList.get(0);

                       LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                       mMap.addMarker(new MarkerOptions().position(latLng).title(address.getCountryName()));
                       mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                   } catch (Exception e) {
                   e.printStackTrace();
               }
            }
    }



    @Override

    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();

        mLocationRequest.setInterval(1000);

        mLocationRequest.setFastestInterval(1000);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(getContext(),

                Manifest.permission.ACCESS_FINE_LOCATION)

                == PackageManager.PERMISSION_GRANTED) {

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        }

    }





    @Override

    public void onConnectionSuspended(int i) {



    }



    @Override

    public void onLocationChanged(Location location) {

        Log.d("onLocationChanged", "entered");



        mLastLocation = location;

        if (mCurrLocationMarker != null) {

            mCurrLocationMarker.remove();

        }



        //Place current location marker

        latitude = location.getLatitude();

        longitude = location.getLongitude();

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(latLng);

        markerOptions.title("Current Position");

        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        mCurrLocationMarker = mMap.addMarker(markerOptions);



        //getNearbyParks();

        mMap.setOnMarkerClickListener(this);







        //move map camera

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));



        Log.d("onLocationChanged", String.format("latitude:%.3f longitude:%.3f",latitude,longitude));



        //stop location updates

        if (mGoogleApiClient != null) {

            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

            Log.d("onLocationChanged", "Removing Location Updates");

        }

        Log.d("onLocationChanged", "Exit");



    }



    private void getNearbyParks() {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");

        googlePlacesUrl.append("location=" + latitude + "," + longitude);

        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);

        googlePlacesUrl.append("&type=parking");

        googlePlacesUrl.append("&sensor=true");

        googlePlacesUrl.append("&key=" + "AIzaSyD1XwtM5P9rGEwSyvDY4f2W5VqhS85Xdjo");

        String url = googlePlacesUrl.toString();

        Object dataTransfer[] = new Object[2];

        dataTransfer[0]= mMap;

        dataTransfer[1]= url;

        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();

        getNearbyPlacesData.execute(dataTransfer);

    }



    @Override

    public void onConnectionFailed(ConnectionResult connectionResult) {



    }



    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission(){

        if (ContextCompat.checkSelfPermission(getContext(),

                Manifest.permission.ACCESS_FINE_LOCATION)

                != PackageManager.PERMISSION_GRANTED) {



            // Asking user if explanation is needed

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),

                    Manifest.permission.ACCESS_FINE_LOCATION)) {



                // Show an explanation to the user *asynchronously* -- don't block

                // this thread waiting for the user's response! After the user

                // sees the explanation, try again to request the permission.



                //Prompt the user once explanation has been shown

                ActivityCompat.requestPermissions(getActivity(),

                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},

                        MY_PERMISSIONS_REQUEST_LOCATION);





            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),

                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},

                        MY_PERMISSIONS_REQUEST_LOCATION);

            }

            return false;

        } else {

            return true;

        }

    }



    @Override

    public void onRequestPermissionsResult(int requestCode,

                                           String permissions[], int[] grantResults) {

        switch (requestCode) {

            case MY_PERMISSIONS_REQUEST_LOCATION: {

                // If request is cancelled, the result arrays are empty.

                if (grantResults.length > 0

                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                    // permission was granted. Do the

                    // contacts-related task you need to do.

                    if (ContextCompat.checkSelfPermission(getContext(),

                            Manifest.permission.ACCESS_FINE_LOCATION)

                            == PackageManager.PERMISSION_GRANTED) {



                        if (mGoogleApiClient == null) {

                            buildGoogleApiClient();

                        }

                        mMap.setMyLocationEnabled(true);

                    }



                } else {



                    // Permission denied, Disable the functionality that depends on this permission.

                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();

                }

                return;

            }



            // other 'case' lines to check for other permissions this app might request.

            // You can add here other case statements according to your requirement.

        }

    }





    private void setLayoutOnClickListers(View view){

        homeParkingSpace = view.findViewById(R.id.home_parking_space);

        homeSchedule = view.findViewById(R.id.home_schedule);



        homeParkingSpace.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MyParkingSpace.class);

                startActivity(intent);
            }

        });



        homeSchedule.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MapsActivity.class);

                startActivity(intent);

            }

        });



    }



    @Override

    public boolean onMarkerClick(Marker marker) {

        final String address = (String) marker.getTitle();

        input_search = root.findViewById(R.id.input_search);

        input_search.setText(address);

        String park_address = input_search.getText().toString();

        return false;

    }

}