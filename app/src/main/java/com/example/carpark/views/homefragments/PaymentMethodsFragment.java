package com.example.carpark.views.homefragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpark.R;
import com.example.carpark.views.AddCardActivity;

public class PaymentMethodsFragment extends Fragment {
    private TextView card;
    private TextView promoCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_payment_methods, container, false);

//        assert getSupportActionBar() != null;   //null check
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        addCard(root);
        addPromoCode(root);

        return root;
    }


    public void addCard(View view) {
        card = view.findViewById(R.id.add_payment);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddCardActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }


    public void addPromoCode(View view) {
        promoCode = view.findViewById(R.id.add_promo_code);
        promoCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
                t.replace(R.id.home_frame, new PromotionFragment());
                t.addToBackStack(null);
                t.commit();
            }
        });
    }


}
