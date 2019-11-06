package com.example.carpark.Api.Responses.Otp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OTPResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("registered")
    @Expose
    private boolean registered;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

}
