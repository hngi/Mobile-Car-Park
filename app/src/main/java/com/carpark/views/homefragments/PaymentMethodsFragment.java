package com.carpark.views.homefragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.carpark.R;
import com.carpark.views.AddCardActivity;
import com.carpark.views.BarterActivity;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

import static com.facebook.FacebookSdk.getApplicationContext;
// import com.carpark.views.BarterActivity;

public class PaymentMethodsFragment extends Fragment {
    private TextView card;
    private TextView promoCode;
    private TextView paystack, barter;
    private Button payStackBut, barterBtn;
    ProgressBar paymentBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_payment_methods, container, false);

//        assert getSupportActionBar() != null;   //null check
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button
        addCard(root);
        addPromoCode(root);
        paymentBar = root.findViewById(R.id.paymentBar);

        paystack = root.findViewById(R.id.paystack);
        payStackBut = root.findViewById(R.id.paystack_btn);


       // paymentBar.setVisibility(View.INVISIBLE);


        PaystackSdk.initialize(getActivity());


        barter = root.findViewById(R.id.barter);
        barterBtn = root.findViewById(R.id.barter_btn);
        openBarter();


        paystack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentBar.setVisibility(View.VISIBLE);
                performCharge();
            }
        });
        payStackBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               paystack.callOnClick();
            }
        });
        return root;


    }


    private void performCharge() {

        String cardNumber = "4084084084084081";

        int expiryMonth = 11; //any month in the future

        int expiryYear = 2020; // any year in the future

        String cvv = "408";
        //create a Charge object
        Charge charge = new Charge();

        Card card = new Card(cardNumber, expiryMonth, expiryYear, cvv);

        if (card.isValid()) {
            //set the card to charge
            charge.setCard(card);

            //call this method if you set a plan
            //charge.setPlan("PLN_yourplan");

            charge.setEmail("noelnwaelugo@gmail.com"); //dummy email address

            charge.setAmount(100); //test amount

            PaystackSdk.chargeCard(getActivity(), charge, new Paystack.TransactionCallback() {
                @Override
                public void onSuccess(Transaction transaction) {
                    // This is called only after transaction is deemed successful.
                    // Retrieve the transaction, and send its reference to your server
                    // for verification.
                    String paymentReference = transaction.getReference();
                    paymentBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Transaction Successful! payment reference: "
                            + paymentReference, Toast.LENGTH_LONG).show();
                }

                @Override
                public void beforeValidate(Transaction transaction) {
                    // This is called only before requesting OTP.
                    // Save reference so you may send to server. If
                    // error occurs with OTP, you should still verify on server.
                }

                @Override
                public void onError(Throwable error, Transaction transaction) {
                    paymentBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "An error occured: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    //handle error here
                }
            });
        } else {
            paymentBar.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "Card invalid", Toast.LENGTH_LONG).show();
        }


    }


    private void openBarter() {
        barterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barter.callOnClick();
            }
        });
        barter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BarterActivity.class));
            }
        });
    }


    private void addCard(View view) {
        card = view.findViewById(R.id.add_payment);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddCardActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }


    private void addPromoCode(View view) {
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
