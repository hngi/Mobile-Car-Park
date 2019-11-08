package com.carpark.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.braintreepayments.cardform.OnCardFormFieldFocusedListener;
import com.braintreepayments.cardform.OnCardFormValidListener;
import com.braintreepayments.cardform.utils.CardType;
import com.braintreepayments.cardform.view.CardEditText;
import com.braintreepayments.cardform.view.CardForm;
import com.carpark.R;
import com.vinaygaba.creditcardview.CreditCardView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 22/10/19
 */

public class AddCardActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "AddCardActivity";
    private CardForm cardForm;
    private CreditCardView creditCardView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);

        Toolbar toolbar = findViewById(R.id.addcard_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
        cardListener();

    }


    private void init() {
        findViewById(R.id.addcard_save_btn).setOnClickListener(this);
        cardForm = findViewById(R.id.addcard_card_form);
        creditCardView = findViewById(R.id.addcard_creditCardView);
        setUpCardForm();
        setUpCardCreditCardView();
    }

    private void setUpCardCreditCardView() {
        //creditCardView.setBackgroundResource(R.drawable.card);
        creditCardView.setCardFrontBackground(R.drawable.button_curve);
    }

    private void setUpCardForm() {
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .cardholderName(CardForm.FIELD_REQUIRED)
                /*.postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")*/
                .actionLabel("Add Card")
                .setup(this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
    }

    private void cardListener() {
        cardForm.setOnFormFieldFocusedListener(new OnCardFormFieldFocusedListener() {
            @Override
            public void onCardFormFieldFocused(View field) {
                displayExpiryData();
            }
        });

        cardForm.setOnCardTypeChangedListener(new CardEditText.OnCardTypeChangedListener() {
            @Override
            public void onCardTypeChanged(CardType cardType) {
                int frontResource = cardType.getFrontResource();
                creditCardView.setCardNumber(cardForm.getCardNumber());


            }
        });

        cardForm.setOnCardFormValidListener(new OnCardFormValidListener() {
            @Override
            public void onCardFormValid(boolean valid) {
                showToast("Card is valid");
                Log.d(TAG, "onCardFormFieldFocused: Card is valid ");
            }
        });

        cardForm.getCardholderNameEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                creditCardView.setCardName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void displayExpiryData() {
        String expirationMonth = cardForm.getExpirationMonth();
        String expirationYear = cardForm.getExpirationYear();

        if (!expirationMonth.equals("") && !expirationYear.equals("")) {
            creditCardView.setExpiryDate(expirationMonth + "/" + expirationYear);
        }
    }

    private void validateCardData() {
        if (cardForm.isValid()) {
            String cardNumber = cardForm.getCardNumber();
            String expirationMonth = cardForm.getExpirationMonth();
            String expirationYear = cardForm.getExpirationYear();
            String cvv = cardForm.getCvv();
            String cardholderName = cardForm.getCardholderName();
            /*String postalCode = cardForm.getPostalCode();
            String countryCode = cardForm.getCountryCode();
            String mobileNumber = cardForm.getMobileNumber();*/

            startActivity(new Intent(this, HomeActivity.class));
            showToast("Card added successfully");

        } else {
            cardForm.validate();
        }
    }

    private void scanCard() {
        if (cardForm.isCardScanningAvailable()) {
            cardForm.scanCard(this);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addcard_save_btn) {
            validateCardData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.addcard_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*if (item.getItemId() == R.id.addcard_menu_scan) {
            scanCard();
        }*/
        return super.onOptionsItemSelected(item);
    }

}
