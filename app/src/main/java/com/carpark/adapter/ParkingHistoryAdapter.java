package com.carpark.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.carpark.Model.review.ParkingHistoryModel;
import com.carpark.R;
import com.carpark.views.DetailsActivity;
import com.carpark.views.HomeActivity;
import com.carpark.views.homefragments.ParkingHistoryFragment;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ParkingHistoryAdapter extends RecyclerView.Adapter<ParkingHistoryAdapter.CustomViewHolder>{
    private Context context;
    private List<ParkingHistoryModel> parkingHistory;
    private Button re_book;

    public ParkingHistoryAdapter(Context context, List<ParkingHistoryModel> parkingHistory) {
        this.context = context;
        this.parkingHistory  = parkingHistory;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View view;
        TextView date_time;
        TextView location;
        TextView qr_code;
        TextView amount;
        Button re_book;

        CustomViewHolder(View itemView) {
            super(itemView);
            view = itemView;

            date_time = view.findViewById(R.id.ph_date_time);
            location = view.findViewById(R.id.ph_location);
            qr_code = view.findViewById(R.id.ph_qr_code);
            amount = view.findViewById(R.id.ph_amount);
            re_book = view.findViewById(R.id.ph_btn_rebook);
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

        //For testing purposes
        holder.date_time.setText(parkingHistory.get(position).getParkingHistoryDate()+" "+parkingHistory.get(position).getParkingHistoryTime());
        holder.location.setText(parkingHistory.get(position).getLocation());
        holder.qr_code.setText(parkingHistory.get(position).getQrCode());
        holder.amount.setText("NGN"+parkingHistory.get(position).getAmount());

        holder.re_book.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Re-Book", Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return parkingHistory.size();
    }

    private void setLayoutOnClickListers(View view) {

        re_book = view.findViewById(R.id.ph_btn_rebook);


        re_book.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent m = new Intent(context, DetailsActivity.class);

                Bundle a = new Bundle();
                a.putString("location", "parkingHistoryDate");
                m.putExtra("location", a);
                context.startActivity(m);
            }

        });
    }
}
