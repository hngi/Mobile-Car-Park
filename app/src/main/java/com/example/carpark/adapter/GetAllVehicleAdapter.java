package com.example.carpark.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpark.Model.Vehicle;

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

    class CostomViewHolder extends RecyclerView.ViewHolder{

        public CostomViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public GetAllVehicleAdapter.CostomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllVehicleAdapter.CostomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
