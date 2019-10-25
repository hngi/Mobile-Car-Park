package com.example.carpark;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class MyVehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_vehicle);
        Toolbar toolbar = findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transactionIntent = new Intent(MyVehicleActivity.this, TransactionActivity.class);
                startActivity(transactionIntent);
                Snackbar.make(view, "Add new Vehicle", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

}
