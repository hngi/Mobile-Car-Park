package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.carpark.R;

import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

public class DetailsActivity extends AppCompatActivity {
    FlipperLayout flipperLayout;
    Button Book;

    //Todo: Note, Most images are placeholders

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        flipperLayout = findViewById(R.id.flipper_layout);
        setLayout();
        Book = findViewById(R.id.bookButton);

        Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: Replace with your own command
                Toast.makeText(DetailsActivity.this, "I was clicked", Toast.LENGTH_SHORT).show();
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
}