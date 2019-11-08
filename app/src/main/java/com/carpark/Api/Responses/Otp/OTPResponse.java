package com.carpark.Api.Responses.Otp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 06/10/19
 */

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
