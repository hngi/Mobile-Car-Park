package com.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carpark.R;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class DetailsActivity extends AppCompatActivity {
    FlipperLayout flipperLayout;
    RelativeLayout address_picker;
    TextView p_address;

    //Todo: Note, Most images are placeholders

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //This is just a test. Will improve this....
        Intent m =getIntent();
        Bundle a = m.getBundleExtra("location");
        String location = a.getString("location");

        String park_address = getIntent().getStringExtra("address"); //this is just a test. Will improve this later...

        getSupportActionBar().setTitle(park_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        flipperLayout = findViewById(R.id.flipper_layout);
        setLayout();

        address_picker = findViewById(R.id.ad_rel_layout);

        p_address = findViewById(R.id.park_address);
        p_address.setText(park_address);

        address_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this, ParkLocation.class);
                startActivity(intent);
            }
        });

    }

    private void setLayout() {
        final Drawable[] images = new Drawable[]{
                getDrawable(R.drawable.bartar), getDrawable(R.drawable.placeholder), getDrawable(R.drawable.ic_launcher_foreground)
        };

        for (int i = 0; images.length > i; i++) {
            FlipperView view = new FlipperView(getBaseContext());
            view.setImageDrawable(images[i]);
            view.setDescription("");
            view.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            flipperLayout.addFlipperView(view);

            flipperLayout.setCircularIndicatorLayoutParams(150, 10);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    Toast.makeText(DetailsActivity.this, "I was clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void book(View view){
        Intent intent = new Intent(DetailsActivity.this, ScheduleActivity.class);
        startActivity(intent);
    }
}