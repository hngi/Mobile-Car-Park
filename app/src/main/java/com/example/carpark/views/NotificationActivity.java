package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.carpark.R;
import com.example.carpark.adapter.NotificationAdapter;

import java.util.ArrayList;

import Model.NotificationModel;

public class NotificationActivity extends AppCompatActivity {
    private Toolbar toolbar;
    ArrayList<NotificationModel> Notifications;
    private NotificationAdapter notificationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initViews();
    }

    private void initViews() {
        toolbar = findViewById( R.id.notif_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DummyData();
        RecyclerView recyclerView = findViewById(R.id.notif_recycler);
        recyclerView.setAdapter(notificationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void DummyData() {
        Notifications = new ArrayList<>();
        Notifications.add(new NotificationModel("Your parking session is ending in 10mins at 11:00AM", R.drawable.notification_image, "Just now" ));
        Notifications.add(new NotificationModel("Your new payment method has been added successfully", R.drawable.notification_logo_two, "10:00AM" ));
        Notifications.add(new NotificationModel("You have a new park suggestion: Tafawa Balewa Park", R.drawable.notification_image, "Wed at 10:00AM" ));
        Notifications.add(new NotificationModel("Check out this park near you: Lekki gardens park", R.drawable.notification_image, "Tue at 10:00AM" ));
        Notifications.add(new NotificationModel("Payment successful. Check receipt ", R.drawable.notification_logo_two, "Wed at 10:00AM" ));
        Notifications.add(new NotificationModel("Your app is out of date. Update to the latest version.", R.drawable.notification_image_three, "Wed at 10:00AM" ));
        Notifications.add(new NotificationModel("You have a new park suggestion: Tafawa Balewa Park", R.drawable.notification_image, "Tue at 10:00AM" ));
        notificationAdapter = new NotificationAdapter(Notifications, this);

    }
}
