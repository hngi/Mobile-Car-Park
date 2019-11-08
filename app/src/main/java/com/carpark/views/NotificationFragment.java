package com.carpark.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.carpark.Model.review.NotificationModel;
import com.carpark.R;
import com.carpark.adapter.NotificationAdapter;


import java.util.ArrayList;


public class NotificationFragment extends Fragment {
    private Toolbar toolbar;
    ArrayList<NotificationModel> Notifications;
    private NotificationAdapter notificationAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = root.findViewById(R.id.notif_recycler);
        initViews();
        return root;
    }

    private void initViews() {
//        toolbar = root.findViewById( R.id.notif_toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Notifications = new ArrayList<>();
        //DummyData();
        recyclerView.setAdapter(notificationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationAdapter = new NotificationAdapter(Notifications, getContext());
    }

    private void DummyData() {

        Notifications.add(new NotificationModel("Your parking session is ending in 10mins at 11:00AM", R.drawable.notification_image, "Just now" ));
        Notifications.add(new NotificationModel("Your new payment method has been added successfully", R.drawable.notification_logo_two, "10:00AM" ));
        Notifications.add(new NotificationModel("You have a new park suggestion: Tafawa Balewa Park", R.drawable.notification_image, "Wed at 10:00AM" ));
        Notifications.add(new NotificationModel("Check out this park near you: Lekki gardens park", R.drawable.notification_image, "Tue at 10:00AM" ));
        Notifications.add(new NotificationModel("Payment successful. Check receipt ", R.drawable.notification_logo_two, "Wed at 10:00AM" ));
        Notifications.add(new NotificationModel("Your app is out of date. Update to the latest version.", R.drawable.notification_image_three, "Wed at 10:00AM" ));
        Notifications.add(new NotificationModel("You have a new park suggestion: Tafawa Balewa Park", R.drawable.notification_image, "Tue at 10:00AM" ));


    }
}
