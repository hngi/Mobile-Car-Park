package com.example.carpark.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.*;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.example.carpark.R;
import com.hbb20.CountryCodePicker;

import java.util.Arrays;

public class GetStarted extends AppCompatActivity {

    private static final String TAG = "GetStarted";
    private CallbackManager callbackManager;
    private static final String AUTH_TYPE = "rerequest";
    private static final String EMAIL = "email";
    EditText number;
    Button fbbtn;
    CountryCodePicker ccp;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        fbbtn = findViewById(R.id.fb_btn);
        final EditText number = (EditText) findViewById(R.id.number);
        final CountryCodePicker ccp = (CountryCodePicker) findViewById(R.id.ccp);

        // facebook authentication
        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();

        fbbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().setAuthType(AUTH_TYPE)
                        .logInWithReadPermissions(GetStarted.this, Arrays.asList(EMAIL));
                facebookLogin();

            }
        });


        // the below code enables the next button on the keyboard to work
        number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                intent = new Intent(getApplicationContext(), VerifyNumber.class);
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

    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is logged in through facebook
        checkLoginStatus();
    }

    private void checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken().isExpired()) {
            // user already signed in
            startActivity(new Intent(GetStarted.this, HomeActivity.class));
            finish();
        }
    }

    public void facebookLogin() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(GetStarted.this, "Successful", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onSuccess: " + loginResult.getAccessToken().getUserId());
                setResult(RESULT_OK);
                startActivity(new Intent(GetStarted.this, HomeActivity.class));
                finish();
                /*call : loginResult.getAccessToken().getUserId() to get userId and save to database;*/
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(GetStarted.this, "Error " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}

