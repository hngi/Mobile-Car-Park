package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.carpark.R;

public class VerifyNumber extends AppCompatActivity {

    Button next;
    EditText etPhoneNumer;
    TextView tvCountryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_number);

        ImageButton back = (ImageButton) findViewById(R.id.imageButton2);

        next = findViewById(R.id.next1);
        etPhoneNumer= findViewById(R.id.editText2);
        tvCountryCode = findViewById(R.id.vn_code);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
                Intent intToMain = new Intent(getApplicationContext(), EnterOTP.class);
                intToMain.putExtra("PhoneNumber", tvCountryCode.getText().toString()+etPhoneNumer.getText().toString());
                startActivity(intToMain);
            }

        });

        // this allowsthe passed edittext from getstarted to show
        String countryCode = getIntent().getStringExtra("countryCode");
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        etPhoneNumer.setText(phoneNumber);
        tvCountryCode.setText(countryCode);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerifyNumber.this, GetStarted.class);
                startActivity(i);
               // finish();
            }
        });
    }
}
