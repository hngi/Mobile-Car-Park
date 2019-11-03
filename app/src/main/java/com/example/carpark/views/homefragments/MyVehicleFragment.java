package com.example.carpark.views.homefragments;

import android.content.Intent;
import android.os.Bundle;

import com.example.carpark.Model.Vehicle;
import com.example.carpark.R;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyVehicleFragment extends Fragment {
    private List<Vehicle> vehicleList;
    private RecyclerView recyclerView;
    private MyVehicleAdapter myVehicleAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root =  inflater.inflate(R.layout.fragment_my_vehicle, container, false);

        FloatingActionButton fab = root.findViewById(R.id.mv_add_vehicle);
        TextView new_text = root.findViewById(R.id.new_text);
        new_text.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transactionIntent = new Intent(getContext(), CarDetailsActiviy.class);
                startActivity(transactionIntent);
            }
        });

        //TEST DATA
        vehicleList = new ArrayList<>();
        Vehicle a = new Vehicle();
        a.setPlateNumber("23WEH0");
        a.setMakeModel("Lexus RX330");

        Vehicle b = new Vehicle();
        b.setPlateNumber("LAG684");
        b.setMakeModel("Toyota Camry");

        Vehicle c = new Vehicle();
        c.setPlateNumber("FTR911");
        c.setMakeModel("Mercedes GLX 450");

        Vehicle d = new Vehicle();
        d.setPlateNumber("67YTE0");
        d.setMakeModel("Kia Sportage");

        Vehicle e = new Vehicle();
        e.setPlateNumber("LEX300");
        e.setMakeModel("Range Rover Evoque");

        vehicleList.add(a);
        vehicleList.add(b);
        vehicleList.add(c);
        vehicleList.add(d);
        vehicleList.add(e);

        recyclerView = root.findViewById(R.id.mv_recyclerView);
        myVehicleAdapter = new MyVehicleAdapter(this.getContext(), vehicleList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myVehicleAdapter);

        return root;
    }
}
