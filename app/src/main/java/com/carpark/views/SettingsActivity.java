package com.carpark.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.carpark.Model.User;
import com.carpark.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.carpark.utils.Commons.getUser;

public class SettingsActivity extends BaseActivity {

    TextView name_text;
    TextView phone_text;
    CircleImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        name_text = findViewById(R.id.name_text);
        phone_text = findViewById(R.id.number_text);
        profile_image = findViewById(R.id.profile_image);
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = getStoredUser();
        if (user != null) {
            String fullName = user.getFirstName() + " " + user.getLastName();
            name_text.setText(fullName);
            phone_text.setText(user.getPhone());

            name_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
                }
            });

            profile_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}