package com.carpark.Api.Responses.LoginReg;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 06/10/19
 */

public class VerificationResponse {

    @SerializedName("registered")
    @Expose
    private boolean registered;

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

}
