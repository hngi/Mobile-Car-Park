package com.carpark.views.homefragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.carpark.Api.ParkingApi;
import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Api.RetrofitClient;
import com.carpark.Model.Vehicle;
import com.carpark.R;
import com.carpark.adapter.MyVehicleAdapter;
import com.carpark.utils.SharePreference;
import com.carpark.views.CarDetailsActiviy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

public class MyVehicleFragment extends Fragment {
    private List<Vehicle> vehicleList;
    private RecyclerView recyclerView;
    private MyVehicleAdapter myVehicleAdapter;
    private ProgressBar progressBar;
    private TextView new_text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root =  inflater.inflate(R.layout.fragment_my_vehicle, container, false);

        FloatingActionButton fab = root.findViewById(R.id.mv_add_vehicle);
        new_text = root.findViewById(R.id.new_text);
        progressBar = root.findViewById(R.id.progressBar);
        new_text.setVisibility(View.INVISIBLE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transactionIntent = new Intent(getContext(), CarDetailsActiviy.class);
                startActivity(transactionIntent);
            }
        });
        recyclerView = root.findViewById(R.id.mv_recyclerView);
        myVehicleAdapter = new MyVehicleAdapter(this.getContext(), vehicleList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(layoutManager);
        vehicleList = new ArrayList<>();
        myVehicleAdapter = new MyVehicleAdapter(getContext(), vehicleList );
        recyclerView.setAdapter(myVehicleAdapter);

        getVehicles();
        return root;
    }

    private void getVehicles() {
        String token = SharePreference.getINSTANCE(getContext()).getAccessToken();
        ParkingApi parkingApi = RetrofitClient.getInstance().create(ParkingApi.class);
        parkingApi.getAllVehicles(token).enqueue(new Callback<BaseDataResponse<List<Vehicle>>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<List<Vehicle>>> call, Response<BaseDataResponse<List<Vehicle>>> response) {
                if(response.isSuccessful()){
                    Log.e("Response code", String.valueOf(response.code()));
                    new_text.setVisibility(View.INVISIBLE);
                    vehicleList.addAll(response.body().getData());
                    myVehicleAdapter.notifyDataSetChanged();
                    if(vehicleList.isEmpty()){
                        new_text.setVisibility(View.VISIBLE);
                    }
                    progressBar.setVisibility(View.GONE);
                }else{
                    Log.e("Response code", String.valueOf(response.code()));
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse<List<Vehicle>>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to retrieve items", Toast.LENGTH_LONG).show();
                Log.e("On Failure", t.getMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
