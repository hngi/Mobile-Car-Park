package com.example.carpark.views;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Model.Vehicle;
import com.example.carpark.R;
import com.example.carpark.adapter.GetAllVehicleAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author .: Utibe Etim
 * @email ..: etim.utibe@gmail.com
 * @created : 06/11/19
 */

public class AllVehicleActivity extends BaseActivity {
    public Activity activity;
    public Dialog dialog;
    TextView mPlateNumber, mCarModel;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter adapter;
    private List<Vehicle> vehicles;

//    public AllVehicleActivity(Context context, int themeResId) {
//        super(context, themeResId);
//    }
//
//    public AllVehicleActivity(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_car_details);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mPlateNumber = findViewById(R.id.get_all_plate_number_tv);
        mCarModel = findViewById(R.id.get_all_vehicle_model_tv);
        recyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);

        vehicles = new ArrayList<>();
        adapter=new GetAllVehicleAdapter(this, vehicles);

        recyclerView.setAdapter(adapter);
        getAllVehicles();
    }

    private void getAllVehicles() {
        getParkingApi().getAllVehicles(getSharePref().getAccessToken()).enqueue(new Callback<BaseDataResponse<List<Vehicle>>>() {
            @Override
            public void onResponse(Call<BaseDataResponse<List<Vehicle>>> call, Response<BaseDataResponse<List<Vehicle>>> response) {
                if (response.isSuccessful()){
                    vehicles = response.body().getData();
                    adapter.summitList();

                }
            }

            @Override
            public void onFailure(Call<BaseDataResponse<List<Vehicle>>> call, Throwable t) {

            }
        });
    }

//    public AllVehicleActivity (Activity activity, RecyclerView.Adapter adapter){
//        super(activity);
//        this.activity = activity;
//        this.adapter = adapter;
//        setUpLayout();
//
//    }

    private void setUpLayout() {
    }
}