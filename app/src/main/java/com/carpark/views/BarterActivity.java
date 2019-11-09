package com.carpark.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.carpark.R;
import com.carpark.views.homefragments.PaymentMethodsFragment;
import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;


import androidx.annotation.Nullable;


/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 28/10/19
 */


public class BarterActivity extends BaseActivity {
    final String publicKey = "FLWPUBK-8def02d7b4068566182283a3b07c2b12-X";
    final String encryptionKey = "61a9ff665af4c17ac919790a";
    private String country;
    private String currency;
    private String email;
    private String firstName;
    private String lastName;
    private Double amount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
        makePay();

    }

    private void initData() {
        country = "NG";
        currency = "NGN";
        email = "ehmaugbogo@gmail.com";
        firstName = "Ehma";
        lastName = "Ugbogo";
        amount = 1000.50;
    }

    private void makePay() {
        new RavePayManager(this)
                .setAmount(amount)
                .setCountry(country)
                .setCurrency(currency)
                .setEmail(email)
                .setfName(firstName)
                .setlName(lastName)
                .setNarration("Parking Payment")
                .setPublicKey(publicKey)
                .setEncryptionKey(encryptionKey)
                .setTxRef(System.currentTimeMillis() + "Ref")
                .acceptAccountPayments(true)
                .acceptCardPayments(true)
                .allowSaveCardFeature(true)
                .withTheme(R.style.FlutterwaveTheme)
                .isPreAuth(true)
                .shouldDisplayFee(true)
                .showStagingLabel(false)
                .initialize();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
         *  We advise you to do a further verification of transaction's details on your server to be
         *  sure everything checks out before providing service or goods.
         */
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                showToast("SUCCESS " + message);
            } else if (resultCode == RavePayActivity.RESULT_ERROR) {
                showToast("ERROR " + message);
            } else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                //showToast("CANCELLED " + message);
                finish();
            }



        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
