package com.example.carpark.views.homefragments;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.carpark.Api.ParkingApi;
import com.example.carpark.Api.Responses.Park.ParkingSpaceAllResponse;
import com.example.carpark.Api.RetrofitClient;
import com.example.carpark.Model.Park.ParkAddress;
import com.example.carpark.Model.Park.ParkingSpace;
import com.example.carpark.Model.Vehicle;
import com.example.carpark.R;
import com.example.carpark.adapter.AddressAdapter;
import com.example.carpark.adapter.MyVehicleAdapter;
import com.example.carpark.views.CarDetailsActiviy;
import com.example.carpark.views.TransactionActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private AddressAdapter addressAdapter;
    private List<ParkingSpace> ParkingSpace;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root =  inflater.inflate(R.layout.fragment_list, container, false);
        Context context = getActivity();
        ParkingSpace = new ArrayList<>();
        recyclerView = root.findViewById(R.id.map_list_view);
        progressBar = root.findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.VISIBLE);
        addressAdapter = new AddressAdapter(getContext(), ParkingSpace );
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addressAdapter);
        String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9obmctY2FyLXBhcmstYXBpLmhlcm9rdWFwcC5jb21cL2FwaVwvdjFcL2F1dGhcL3ZlcmlmeS1vdHAiLCJpYXQiOjE1NzMwMTQ4NTQsImV4cCI6MTU3MzEyMjg1NCwibmJmIjoxNTczMDE0ODU0LCJqdGkiOiJXQ0VydHZlZFV4RFBZdzB3Iiwic3ViIjoxOCwicHJ2IjoiODdlMGFmMWVmOWZkMTU4MTJmZGVjOTcxNTNhMTRlMGIwNDc1NDZhYSJ9.RMo2rLPq5YF6D3aau3N8NBg6D7hmeOTYntMySf5fizo";
        ParkingApi parkingApi = RetrofitClient.getInstance().create(ParkingApi.class);
        parkingApi.getAllParkingSpace(token).enqueue(new Callback<ParkingSpaceAllResponse>() {
            @Override
            public void onResponse(Call<ParkingSpaceAllResponse> call, Response<ParkingSpaceAllResponse> response) {
                if(response.isSuccessful()){
                    Log.e("Response code", String.valueOf(response.code()));
                    Toast.makeText(getContext(),"Successful"+String.valueOf(response.code()),Toast.LENGTH_LONG).show();
                    ParkingSpace.addAll(response.body().getParkingSpaces());
                    addressAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(getContext(),"Failure"+String.valueOf(response.code()),Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ParkingSpaceAllResponse> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        return root;
    }
}
