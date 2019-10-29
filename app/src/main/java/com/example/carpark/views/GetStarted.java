package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.example.carpark.R;
import com.hbb20.CountryCodePicker;

public class GetStarted extends AppCompatActivity {

    EditText number;
    Button fb_btn, cont_btn;
    CountryCodePicker ccp;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        fb_btn = (Button) findViewById(R.id.fb_btn);
         number = (EditText) findViewById(R.id.number);
         ccp = (CountryCodePicker) findViewById(R.id.ccp);
        cont_btn = (Button)findViewById(R.id.getSrt_cont_btn);


        fb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cont_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openVerifyNumber();
            }
        });

        // the below code enables the next button on the keyboard to work
        number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                intent = new Intent(getApplicationContext(), VerifyNumber.class);
//                if(TextUtils.isEmpty(number.getText().toString())){
//                    number.setError("Please fill in phone number");
//                }else {
//                    intent.putExtra("countryCode", String.valueOf(ccp.getSelectedCountryCodeWithPlus()));
//                    intent.putExtra("phoneNumber", number.getText().toString());
//                    startActivity(intent);
//                }
                openVerifyNumber();
                return false;

            }



        });

    }

    private void openVerifyNumber() {
        intent = new Intent(getApplicationContext(), VerifyNumber.class);
        if(TextUtils.isEmpty(number.getText().toString())){
            number.setError("Please fill in phone number");
        }else {
            intent.putExtra("countryCode", String.valueOf(ccp.getSelectedCountryCodeWithPlus()));
            intent.putExtra("phoneNumber", number.getText().toString());
            startActivity(intent);
        }

    }
}

