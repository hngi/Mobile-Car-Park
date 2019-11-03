package com.example.carpark.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpark.Model.ParkAddress;
import com.example.carpark.Model.Vehicle;
import com.example.carpark.R;
import com.example.carpark.views.ScheduleActivity;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.CustomViewHolder> {
    private Context context;
    private List<ParkAddress> parkAddresses;

    public AddressAdapter(Context context, List<ParkAddress> parkAddresses) {
        this.context = context;
        this.parkAddresses  = parkAddresses;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        TextView address_name;
        TextView park_name;
        CardView park_details;
        CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            address_name = view.findViewById(R.id.park_address);
            park_name = view.findViewById(R.id.park_name);
            park_details = view.findViewById(R.id.park_location);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_map, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final String park_name = parkAddresses.get(position).getPark_name();
        final String park_address = parkAddresses.get(position).getPark_address();
        holder.address_name.setText(parkAddresses.get(position).getPark_address());
        holder.park_name.setText(parkAddresses.get(position).getPark_name());
        holder.park_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ScheduleActivity.class);
                i.putExtra("Park_Name",park_name);
                i.putExtra("Park_Address",park_address);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return parkAddresses.size();
    }
}
