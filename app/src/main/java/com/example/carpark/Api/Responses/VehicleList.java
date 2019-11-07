package com.example.carpark.Api.Responses;

import com.example.carpark.Model.Vehicle;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VehicleList<T> {

    @SerializedName("data")
    @Expose
    private List<T> data;

    public List<T> getVehicles() {
            return data;
            }

    public void setVehicles(List<T> vehicles) {
            this.data = vehicles;
            }
}
