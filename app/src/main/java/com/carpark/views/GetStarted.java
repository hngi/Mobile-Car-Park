package com.carpark.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.carpark.Api.ParkingApi;
import com.carpark.Api.Responses.BaseDataResponse;
import com.carpark.Api.Responses.BaseResponse;
import com.carpark.Api.Responses.LoginReg.UserResponse;
import com.carpark.Api.Responses.LoginReg.VerificationResponse;
import com.carpark.Api.Responses.Otp.OTPResponse;
import com.carpark.Api.RetrofitClient;
import com.carpark.IgnoreForApiTest.StartActivity;
import com.carpark.Model.PhoneOtp;
import com.carpark.utils.SharePreference;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.carpark.R;
import com.hbb20.CountryCodePicker;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

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
    ProgressBar sendOTPbar;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        fb_btn = (Button) findViewById(R.id.fb_btn);
        number = (EditText) findViewById(R.id.number);
        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        cont_btn = (ImageView) findViewById(R.id.getSrt_cont_btn);
        sendOTPbar = findViewById(R.id.sendOTPbar);

        // Check if user is already logged in through facebook
        checkLoginStatus();

        fb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFacebookLogin();
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


                return false;
            }

        });
    }

    // facebook login
    private void onFacebookLogin() {
        callbackManager = CallbackManager.Factory.create();

        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Toast.makeText(GetStarted.this, "Successfully logged in with Facebook", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(GetStarted.this, HomeActivity.class);
                intent.putExtra("login_type", "facebook");
                startActivity(intent);
                finish();

                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject json, GraphResponse response) {
                        if (response.getError() != null) {
                            // handle error
                            System.out.println("ERROR");

                        } else {
                            System.out.println("Success");

                            try {

                                String jsonresult = String.valueOf(json);
                                System.out.println("JSON Result" + jsonresult);

                                String email = json.getString("email");
                                String id = json.getString("id");
                                String firstname = json.getString("first_name");
                                String lastname = json.getString("last_name");
                                String phone = json.getString("phone");
                                String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });

                // We set parameters to the GraphRequest using a Bundle
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, email, first_name, last_name, phone");
                request.setParameters(parameters);
                // Initiate the GraphRequest
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                Log.d("TAG_CANCEL", "On cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("TAG_ERROR", error.toString());

            }
        });
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
        if (SharePreference.getINSTANCE(this).getIsUserLoggedIn() == true) {
            // user already signed in
            startActivity(new Intent(GetStarted.this, HomeActivity.class));
            finish();
        }
    }

    private void openVerifyNumber() {
        String Phone = number.getText().toString().trim();
        String countryCode = ccp.getSelectedCountryCode();

        String fullPhone = countryCode + Phone;
        intent = new Intent(getApplicationContext(), VerifyNumber.class);
        if (TextUtils.isEmpty(number.getText().toString())) {
            number.setError("Please fill in phone number");
        } else if (!((Phone.length() < 9) || (Phone.length() > 11))) {
            if (Phone.startsWith("0")) {
                number.setError("Kindly remove the first '0' on the number");
            } else {
                cont_btn.setClickable(false);
                showAlert(fullPhone);
            }
        } else {
            Toast.makeText(GetStarted.this, "Enter a Valid Number", Toast.LENGTH_SHORT).show();
        }
    }


    private void showAlert(final String phoneNumber) {
        Button yes, no;
        TextView phone;
        Toolbar toolbar;

        final AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        //myDialog.setTitle("Confirm Number?");
        final View customView = getLayoutInflater().inflate(R.layout.activity_custom_dialogue, null);
        yes = customView.findViewById(R.id.YesButton);
        no = customView.findViewById(R.id.NoButton);
        phone = customView.findViewById(R.id.confirmNumber);
        //toolbar = customView.findViewById(R.id.custom_toolbar);
        phone.setText(phoneNumber);
        //toolbar.setTitle("Confirm phone Number");

        //myDialog.setTitle("Confirm phone Number").setIconAttribute(getTitleColor());
        myDialog.setView(customView);
        final AlertDialog dialog = myDialog.create();
        dialog.show();
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOTPbar.setVisibility(View.VISIBLE);
                dialog.dismiss();
                cont_btn.setClickable(true);
                verifyNumberIfRegistered(phoneNumber);


            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                cont_btn.setClickable(true);
            }


        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                cont_btn.setClickable(true);
            }
        });


    }


    public void sendOtp(final String phoneForOTP) {
        getParkingApi().sendOTP(phoneForOTP).enqueue(new Callback<OTPResponse>() {
            @Override
            public void onResponse(Call<OTPResponse> call, Response<OTPResponse> response) {
                if (response.isSuccessful()) {

                    boolean registered = response.body().isRegistered();

                    if (registered) {
                        //  getLoginOtpThenLoginOnSuccess(phoneForOTP, "1234");
                    } else {
                        String message = response.body().getMessage();
                        sendOTPbar.setVisibility(View.INVISIBLE);
                        Toast.makeText(GetStarted.this, message, Toast.LENGTH_SHORT).show();
                        //if(message.equals("OTP verified."))
                        Log.d(TAG, "Code: " + response.code() + "message; " + message);
                        Intent intent = new Intent(GetStarted.this, EnterOTP.class);
                        intent.putExtra("PhoneNumberForOTP", phoneForOTP);
                        startActivity(intent);
                    }
                } else {
                    sendOTPbar.setVisibility(View.INVISIBLE);
                    assert response.body().getMessage() != null;
                    String message = response.body().getMessage();
                    cont_btn.setClickable(true);
                    Toast.makeText(GetStarted.this, message, Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Code: " + response.code() + "message; " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<OTPResponse> call, Throwable t) {
                sendOTPbar.setVisibility(View.INVISIBLE);
                Toast.makeText(GetStarted.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                cont_btn.setClickable(true);
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });
    }

    private void verifyNumberIfRegistered(final String phone) {

        getParkingApi().verifyPhoneNo(phone).enqueue(new Callback<VerificationResponse>() {
            @Override
            public void onResponse(Call<VerificationResponse> call, Response<VerificationResponse> response) {
                if (response.isSuccessful()) {
                    boolean registered = response.body().isRegistered();
                    if (!registered) {
                        sendOtp(phone);
                        Log.d(TAG, "Number is not Registered");
                    } else {
                        logInOldUser(phone);
                        Log.d(TAG, "Number is Registered");
                    }
                } else {
                    Log.d(TAG, "SendOTP; Code: " + response.code() + "message; " + response.message());
                    showToast("(sendOTP) Invalid data");
                }
                sendOTPbar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<VerificationResponse> call, Throwable t) {
                sendOTPbar.setVisibility(View.INVISIBLE);
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void logInOldUser(String phone) {
        Intent intent = new Intent(this, PasswordActivity.class);
        intent.putExtra(PasswordActivity.phoneForLoginKEY, phone);
        startActivity(intent);
    }


}


