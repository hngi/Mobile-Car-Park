package com.example.carpark.views.homefragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.carpark.R;
import com.example.carpark.views.PaymentMethodActivity;

public class PromotionFragment extends Fragment {
    EditText rectangle;
    Button btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_promotion, container, false);


        rectangle = root.findViewById(R.id.promotion_rectangle);
        btn = root.findViewById(R.id.promo_btn);

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
                Intent intent = new Intent(getContext(), PaymentMethodActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
//            Todo: getSupportActionBar().setTitle("Apply Promo Code");

}
