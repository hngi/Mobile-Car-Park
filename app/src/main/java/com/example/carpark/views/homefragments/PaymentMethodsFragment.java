package com.example.carpark.views.homefragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carpark.R;

public class PaymentMethodsFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_payment_methods, container, false);

//        assert getSupportActionBar() != null;   //null check
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        return root;
    }


    public void addCard(View view) {
        Intent intent = new Intent(getContext(), AddCardActivity.class);
        startActivity(intent);
    }


    public void addPromoCode(View view) {
        Intent intent = new Intent(getContext(), PromotionsActivity.class);
        startActivity(intent);
    }

    //Remove private class when original AddCardActivity is created
    private class AddCardActivity {
    }

    //Remove private class when original PromotionsActivity is created
    private class PromotionsActivity {
    }
}
