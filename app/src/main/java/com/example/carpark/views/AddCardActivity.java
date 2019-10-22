package com.example.carpark.views;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.carpark.BaseActivity;
import com.example.carpark.R;
import com.fevziomurtekin.payview.Payview;
import com.fevziomurtekin.payview.data.PayModel;

import androidx.annotation.Nullable;

public class AddCardActivity extends BaseActivity {

    private static final String TAG = "AddCardActivity";
    private Payview payview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);

        payview = findViewById(R.id.addcard_payview);

        payListeners();


    }

    private void payListeners() {
        payview.setOnDataChangedListener(new Payview.OnChangelistener() {
            @Override
            public void onChangelistener(@org.jetbrains.annotations.Nullable PayModel payModel) {
                Log.d("PayView", "data : ${payModel?.cardOwnerName} \n " +
                        "is Fill all form component : $isFillAllComponents");
            }
        });

        payview.setPayOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PayView "," clicked. iss Fill all form Component : ${payview.isFillAllComponents}");
                //if(payview.)
            }
        });

    }
}
