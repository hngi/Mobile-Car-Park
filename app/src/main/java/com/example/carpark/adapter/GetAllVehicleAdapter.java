package com.example.carpark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpark.Model.Vehicle;
import com.example.carpark.R;

import java.util.List;

/**
 * @author .: Utibe Etim
 * @email ..: etim.utibe@gmail.com
 * @created : 06/11/19
 */

public class GetAllVehicleAdapter extends RecyclerView.Adapter<GetAllVehicleAdapter.CostomViewHolder> {
    private Context context;
    private List<Vehicle> vehicles;

    public GetAllVehicleAdapter(Context context, List<Vehicle> vehicles){
        this.context = context;
        this.vehicles = vehicles;
    }

    @NonNull
    @Override
    public GetAllVehicleAdapter.CostomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.all_vehicle_item, parent, false);
        return new GetAllVehicleAdapter.CostomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllVehicleAdapter.CostomViewHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);
        holder.mPlate_number.setText(vehicle.getPlateNumber());
        holder.mVehicle_model.setText(vehicle.getMakeModel());
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    private void

    class CostomViewHolder extends RecyclerView.ViewHolder{
        public final View view;
        TextView mPlate_number;
        TextView mVehicle_model;


        public CostomViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;

            mPlate_number = view.findViewById(R.id.get_all_plate_number_tv);
            mVehicle_model = view.findViewById(R.id.get_all_vehicle_model_tv);

        }
    }
}
