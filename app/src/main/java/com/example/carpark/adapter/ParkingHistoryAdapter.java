package com.example.carpark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carpark.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ParkingHistoryAdapter extends RecyclerView.Adapter<ParkingHistoryAdapter.CustomViewHolder>{
    private Context context;
    private List<String> parkingHistory;

    public ParkingHistoryAdapter(Context context, List<String> parkingHistory) {
        this.context = context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        TextView date_time;
        TextView location;
        TextView qr_code;
        TextView amount;

        CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            date_time = view.findViewById(R.id.ph_date_time);
            location = view.findViewById(R.id.ph_location);
            qr_code = view.findViewById(R.id.ph_amount);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.parking_history_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return parkingHistory.size();
    }
}
