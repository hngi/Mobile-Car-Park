package com.carpark.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.carpark.Model.Vehicle;
import com.carpark.R;
import com.carpark.utils.SharePreference;
import com.carpark.views.CarDetailsActiviy;
import com.carpark.views.ScheduleActivity;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * @author .: Osemwingie Oshodin
 * @email ..: osemwingieoshodin@gmail.com
 * @created : 06/11/19
 */

public class BookingVehicleAdapter extends RecyclerView.Adapter<BookingVehicleAdapter.CustomViewHolder> {
    private Context context;
    private List<Vehicle> vehicles;

    public BookingVehicleAdapter(Context context, List<Vehicle> vehicles) {
        this.context = context;
        this.vehicles  = vehicles;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        TextView plate_number;
        TextView vehicle_name;
        TextView edit;
        CardView single_vehicle;

        CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            plate_number = view.findViewById(R.id.mv_plate_number);
            vehicle_name = view.findViewById(R.id.mv_vehicle_name);
            edit = view.findViewById(R.id.mv_edit);
            single_vehicle = view.findViewById(R.id.single_vehicle);
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
        holder.edit.setVisibility(View.GONE);
        holder.single_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePreference.getINSTANCE(context).setMainVehicleId(vehicles.get(position).getId());
                SharePreference.getINSTANCE(context).setMainVehicleName(vehicles.get(position).getMakeModel());
                SharePreference.getINSTANCE(context).setMainVehicleNumber(vehicles.get(position).getPlateNumber());

                Intent i = new Intent(context, ScheduleActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }
}
