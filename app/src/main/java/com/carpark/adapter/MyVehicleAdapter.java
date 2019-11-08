package com.carpark.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.carpark.Model.Vehicle;
import com.carpark.R;
import com.carpark.views.CarDetailsActiviy;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class MyVehicleAdapter extends RecyclerView.Adapter<MyVehicleAdapter.CustomViewHolder> {
    private Context context;
    private List<Vehicle> vehicles;

    public MyVehicleAdapter(Context context, List<Vehicle> vehicles) {
        this.context = context;
        this.vehicles  = vehicles;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        TextView plate_number;
        TextView vehicle_name;
        TextView edit;

        CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            plate_number = view.findViewById(R.id.mv_plate_number);
            vehicle_name = view.findViewById(R.id.mv_vehicle_name);
            edit = view.findViewById(R.id.mv_edit);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.vehicle_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {

        holder.plate_number.setText(vehicles.get(position).getPlateNumber());
        holder.vehicle_name.setText(vehicles.get(position).getMakeModel());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CarDetailsActiviy.class);
                intent.putExtra("Vehicle_Id", vehicles.get(position).getId());
                intent.putExtra("plate_number",vehicles.get(position).getPlateNumber());
                intent.putExtra("make",vehicles.get(position).getMakeModel());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }
}
