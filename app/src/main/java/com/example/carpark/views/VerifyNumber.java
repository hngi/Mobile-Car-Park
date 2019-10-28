package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpark.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyNumber extends AppCompatActivity {

    Button next;
    EditText etPhoneNumer;
    TextView tvCountryCode;
    String phoneNumber;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_number);

        ImageButton back = (ImageButton) findViewById(R.id.imageButton2);

        next = findViewById(R.id.next1);
        etPhoneNumer= findViewById(R.id.editText2);
        tvCountryCode = findViewById(R.id.vn_code);

        // this allowsthe passed edittext from getstarted to show
        final String countryCode = getIntent().getStringExtra("countryCode");
        String phone = getIntent().getStringExtra("phoneNumber");
        etPhoneNumer.setText(phone);
        tvCountryCode.setText(countryCode);

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
               // phoneNumber = etPhoneNumer.getText().toString();
                Intent intToMain = new Intent(getApplicationContext(), EnterOTP.class);
                intToMain.putExtra("PhoneNumber", tvCountryCode.getText().toString()+etPhoneNumer.getText().toString());
                startActivity(intToMain);
            }

        });

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
