package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.carpark.R;

public class PaymentMethodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);
        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void addCard(View view) {
        Intent intent = new Intent(PaymentMethodsActivity.this, AddCardActivity.class);
        startActivity(intent);
    }


    public void addPromoCode(View view) {
        Intent intent = new Intent(PaymentMethodsActivity.this, PromotionsActivity.class);
        startActivity(intent);
    }

    //Remove private class when original AddCardActivity is created
    private class AddCardActivity {
    }

    //Remove private class when original PromotionsActivity is created
    private class PromotionsActivity {
    }
}
