package com.carpark.views;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.carpark.R;

/**
 * @author .: Utibe Etim
 * @email ..: etim.utibe@gmail.com
 * @created : 06/11/19
 */

public class GetAllCarDetailsActivity extends Dialog {
    public Activity activity;
    public Dialog dialog;
    TextView mPlateNumber, mCarModel;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter adapter;

    public GetAllCarDetailsActivity() {
        super(null);
    }

    public GetAllCarDetailsActivity(Context context) {
        super(context);
    }

    public GetAllCarDetailsActivity(Context context, int themeResId) {
        super(context, themeResId);
    }

    public GetAllCarDetailsActivity(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


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


        recyclerView.setAdapter(adapter);
    }

    public GetAllCarDetailsActivity (Activity activity, RecyclerView.Adapter adapter){
        super(activity);
        this.activity = activity;
        this.adapter = adapter;
        setUpLayout();

    }

    private void setUpLayout() {
    }
}
