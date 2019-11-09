package com.carpark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.carpark.Model.review.NotificationModel;
import com.carpark.R;


import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private final Context context;
    private List<NotificationModel> items;

    public NotificationAdapter(List<NotificationModel> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new NotificationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder holder, int position) {
        NotificationModel item = items.get(position);

        holder.mMessage.setText(item.getMessage());
        holder.mTimestamp.setText(item.getTimeStamp());
        holder.mIcon.setImageResource(item.getImageUrl());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mMessage;
        private TextView mTimestamp;
        private CircleImageView mIcon;
        private ImageView imageView;

        private ViewHolder(View itemView) {
            super(itemView);
            mMessage = itemView.findViewById(R.id.notification_message);
            mTimestamp = itemView.findViewById(R.id.notication_timestamp);
            mIcon = itemView.findViewById(R.id.notification_icon);
            imageView = itemView.findViewById(R.id.enter_image);
        }
    }
}