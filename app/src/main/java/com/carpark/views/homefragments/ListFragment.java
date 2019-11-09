package com.carpark.views.homefragments;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.carpark.Api.ParkingApi;
import com.carpark.Api.Responses.Park.ParkingSpaceAllResponse;
import com.carpark.Api.RetrofitClient;
import com.carpark.Model.Park.ParkAddress;
import com.carpark.Model.Park.ParkingSpace;
import com.carpark.Model.Vehicle;
import com.carpark.utils.SharePreference;
import com.carpark.R;
import com.carpark.adapter.AddressAdapter;
import com.carpark.adapter.MyVehicleAdapter;
import com.carpark.views.BaseActivity;
import com.carpark.views.CarDetailsActiviy;
import com.carpark.views.TransactionActivity;
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
        String token = SharePreference.getINSTANCE(getContext()).getAccessToken();
        ParkingApi parkingApi = RetrofitClient.getInstance().create(ParkingApi.class);
        parkingApi.getAllParkingSpace(token).enqueue(new Callback<ParkingSpaceAllResponse>() {
            @Override
            public void onResponse(Call<ParkingSpaceAllResponse> call, Response<ParkingSpaceAllResponse> response) {
                if(response.isSuccessful()){
                    Log.e("Response code", String.valueOf(response.code()));
                    assert response.body() != null;
                    ParkingSpace.addAll(response.body().getParkingSpaces());
                    addressAdapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(getContext(),"Failure "+ response.code(),Toast.LENGTH_LONG).show();
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
