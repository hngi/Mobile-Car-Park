package com.example.carpark.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpark.Api.ParkingApi;
import com.example.carpark.Api.Responses.BaseResponse;
import com.example.carpark.Api.RetrofitClient;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.example.carpark.R;
import com.hbb20.CountryCodePicker;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetStarted extends BaseActivity {

    private static final String TAG = "GetStarted";
    private CallbackManager callbackManager;
    private static final String AUTH_TYPE = "rerequest";
    private static final String EMAIL = "email";
    private EditText number;
    private Button fb_btn;
    private ImageView cont_btn;
    private CountryCodePicker ccp;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        fb_btn = (Button) findViewById(R.id.fb_btn);
        number = (EditText) findViewById(R.id.number);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        cont_btn = (ImageView) findViewById(R.id.getSrt_cont_btn);


        // facebook authentication
        callbackManager = CallbackManager.Factory.create();

        // Check if user is already logged in through facebook
        checkLoginStatus();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(GetStarted.this, "User logged in through Facebook.", Toast.LENGTH_SHORT).show();
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

        fb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().setAuthType(AUTH_TYPE)
                        .logInWithReadPermissions(GetStarted.this, Arrays.asList(EMAIL));
            }
        });

        // phone number verification
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

                return false;
            }

        });
    }

    private void openVerifyNumber() {
        String Phone = number.getText().toString().trim();
        String countryCode = ccp.getSelectedCountryCodeWithPlus();
        String fullPhone = countryCode + Phone;
        intent = new Intent(getApplicationContext(), VerifyNumber.class);
        if (TextUtils.isEmpty(number.getText().toString())) {
            number.setError("Please fill in phone number");
        } else if (!((Phone.length() < 10) || (Phone.length() > 11))) {
            /*intent.putExtra("countryCode", String.valueOf(ccp.getSelectedCountryCodeWithPlus()));
            intent.putExtra("phoneNumber", number.getText().toString());
            startActivity(intent);*/

            showAlert(fullPhone);
        } else {
            Toast.makeText(GetStarted.this, "Enter a Valid Number", Toast.LENGTH_SHORT).show();
        }
    }

    //pass the facebook login results to the LoginManager via callbackManager.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Check if user is already logged in through facebook
    private void checkLoginStatus() {
        if (AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken().isExpired()) {
            // user already signed in
            startActivity(new Intent(GetStarted.this, HomeActivity.class));
            finish();
        }
    }

    private void showAlert(final String phoneNumber) {
        TextView yes, no;
        TextView phone;

        final AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        //myDialog.setTitle("Confirm Number?");
        final View customView = getLayoutInflater().inflate(R.layout.activity_custom_dialogue, null);
        yes = customView.findViewById(R.id.YesButton);
        no = customView.findViewById(R.id.NoButton);
        phone = customView.findViewById(R.id.confirmNumber);
        phone.setText(phoneNumber);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // sendOtp(phoneNumber);

                Intent intent = new Intent(GetStarted.this, EnterOTP.class);
                intent.putExtra("PhoneNumberForOTP", phoneNumber);
                startActivity(intent);
            }
        });
        myDialog.setView(customView);
        final AlertDialog dialog = myDialog.create();
        dialog.show();

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

       /* myDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GetStarted.this, EnterOTP.class);
                intent.putExtra("PhoneNumberForOTP",phoneNumber);
                startActivity(intent);
            }
        });
        myDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });*/


    }

    public void sendOtp(String phoneForOTP) {
        getParkingApi().sendOTP(phoneForOTP).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    Toast.makeText(GetStarted.this, message, Toast.LENGTH_SHORT).show();
                    //if(message.equals("OTP verified."))
                    Log.d(TAG, "Code: " + response.code() + "message; " + message);
                } else {
                    Log.d(TAG, "Code: " + response.code() + "message; " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

}


