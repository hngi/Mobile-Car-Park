package com.example.carpark.views.homefragments;

import android.content.Intent;
import android.os.Bundle;

import com.example.carpark.Model.ParkAddress;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private List<ParkAddress> addresses;
    private RecyclerView recyclerView;
    private AddressAdapter addressAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root =  inflater.inflate(R.layout.fragment_list, container, false);


        //TEST DATA
        addresses = new ArrayList<>();
        ParkAddress a = new ParkAddress();
        a.setPark_address("Ikoyi, Lagos");
        a.setPark_name("Ikoyi car Park");

        ParkAddress b = new ParkAddress();
        b.setPark_address("Murtala Muhammed International Airport, Lagos");
        b.setPark_name("New Airport Car Park");

        ParkAddress c = new ParkAddress();
        c.setPark_address("Ojo, Lagos");
        c.setPark_name("Zone D Car Park");

        ParkAddress d = new ParkAddress();
        d.setPark_address("31 Marina Rd, Lagos Island, Lagos");
        d.setPark_name("Marina Car Park");

        ParkAddress e = new ParkAddress();
        e.setPark_address("Ikeja General Hospital Road, Ikeja GRA, Lagos");
        e.setPark_name("Ikeja General hospital car park");
        ParkAddress f = new ParkAddress();
        f.setPark_address("Ilupeju, Lagos");
        f.setPark_name("Oshodi Car Park");
        ParkAddress g = new ParkAddress();
        g.setPark_address("Mobolaji Johnson Ave, Alausa, Lagos");
        g.setPark_name("Daystar Christian Center Car Park C");
        ParkAddress h = new ParkAddress();
        h.setPark_address("Yaba, Lagos");
        h.setPark_name("Stadium Parking Lot");

        addresses.add(a);
        addresses.add(b);
        addresses.add(c);
        addresses.add(g);
        addresses.add(d);
        addresses.add(e);
        addresses.add(f);
        addresses.add(h);

        recyclerView = root.findViewById(R.id.map_list_view);
        addressAdapter = new AddressAdapter(this.getContext(), addresses);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addressAdapter);

        return root;
    }
}
