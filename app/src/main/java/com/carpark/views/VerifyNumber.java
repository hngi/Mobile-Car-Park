package com.carpark.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.carpark.Api.ParkingApi;
import com.carpark.Api.Responses.BaseResponse;
import com.carpark.Api.RetrofitClient;
import com.carpark.R;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyNumber extends AppCompatActivity {

    Button next;
    EditText etPhoneNumer;
    CountryCodePicker tvCountryCode;
    String phoneNumber, verification_code;
    FirebaseAuth auth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    ProgressBar verifyBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_number);

        ImageButton back = (ImageButton) findViewById(R.id.imageButton2);
        auth = FirebaseAuth.getInstance();

        next = findViewById(R.id.next1);
        etPhoneNumer= findViewById(R.id.verify_number);
        tvCountryCode = findViewById(R.id.verify_ccp);
        verifyBar = findViewById(R.id.progressBarVer);

        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verification_code= s;
                Toast.makeText(getApplicationContext(),"Code sent to number", Toast.LENGTH_LONG).show();
            }
        };



        // this allowsthe passed edittext from getstarted to show
        final String countryCode = getIntent().getStringExtra("countryCode");
        String phone = getIntent().getStringExtra("phoneNumber");
        etPhoneNumer.setText(phone);
        tvCountryCode.setFullNumber(countryCode);
        final String phoneForOTP = etPhoneNumer.getText().toString();
        String countryCodeForOTP = tvCountryCode.getFullNumber();
        final String numberForOTP = countryCodeForOTP+phoneForOTP;








        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {
               // phoneNumber = etPhoneNumer.getText().toString();
//                sendFBOTP();
//                Intent i = new Intent(VerifyNumber.this, EnterOTP.class);
//                startActivity(i);
                if (!((phoneForOTP.length() <10))){
                    verifyBar.setVisibility(View.VISIBLE);
                  //  SendOtp(phoneForOTP);

               // SendOtp(numberForOTP);
                    Intent i = new Intent(VerifyNumber.this, EnterOTP.class);
                    startActivity(i);


                }else {
                    Toast.makeText(VerifyNumber.this, "Enter a Valid Number", Toast.LENGTH_SHORT).show();
                }
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

    public void sendFBOTP(){
       phoneNumber = etPhoneNumer.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,60,TimeUnit.SECONDS,this,mCallback
        );
    }

    public void signInwithPhone(PhoneAuthCredential credential){
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(),"User has Logged in successfully", Toast.LENGTH_LONG).show();
                    }
                });
    }




}
