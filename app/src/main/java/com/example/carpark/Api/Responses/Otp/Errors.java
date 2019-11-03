package com.example.carpark.Api.Responses.Otp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Errors {

    @SerializedName("phone")
    @Expose
    private List<String> phone = null;

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

}
