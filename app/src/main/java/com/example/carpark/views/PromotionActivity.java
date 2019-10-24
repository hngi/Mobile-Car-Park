package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carpark.R;

public class PromotionActivity extends AppCompatActivity {
    EditText rectangle;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        //change action bar title
        getSupportActionBar().setTitle("Apply Promo Code");

        //find views by id
        rectangle = findViewById(R.id.promotion_rectangle);
        btn = findViewById(R.id.promo_btn);

        //set on click listener on button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Promo button clicked", Toast.LENGTH_SHORT).show();
                String editText = rectangle.getText().toString();
                if(TextUtils.isEmpty(editText)){
                    rectangle.setError("Please enter a promotion code");
                    rectangle.requestFocus();
                    return;
                }
                Intent intent = new Intent(PromotionActivity.this, PaymentMethodsActivity.class);
                startActivity(intent);
            }
        });
    }
}
