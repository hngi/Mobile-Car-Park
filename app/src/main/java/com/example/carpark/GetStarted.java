package com.example.carpark;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.util.StringUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;

public class GetStarted extends AppCompatActivity {

    EditText number;
    Button fbbtn;
    CountryCodePicker ccp;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        final Button fbbtn = (Button) findViewById(R.id.fb_btn);
        final EditText number = (EditText) findViewById(R.id.number);
        final CountryCodePicker ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ImageButton forward = (ImageButton) findViewById(R.id.forward);


        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // the below code enables the next button on the keyboard to work
        number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                intent = new Intent(getApplicationContext(),VerifyNumber.class);
                if(TextUtils.isEmpty(number.getText().toString())){
                    number.setError("Please fill in phone number");
                }else {
                    intent.putExtra("countryCode", String.valueOf(ccp.getSelectedCountryCodeWithPlus()));
                    intent.putExtra("phoneNumber", number.getText().toString());
                    startActivity(intent);
                }
                return false;

            }



        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(number.getText().toString())){
                    number.setError("Please fill in phone number");
                }else {
                    intent = new Intent(getApplicationContext(),VerifyNumber.class);
                    intent.putExtra("countryCode", String.valueOf(ccp.getSelectedCountryCodeWithPlus()));
                    intent.putExtra("phoneNumber", number.getText().toString());
                    startActivity(intent);
                }
            }
        });



    }}

