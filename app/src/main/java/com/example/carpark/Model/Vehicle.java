package com.example.carpark.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 31/10/19
 */

public class Vehicle {
    public Vehicle(String plateNumber, String makeModel, boolean mainRide) {
        this.plateNumber = plateNumber;
        this.makeModel = makeModel;
        this.mainRide = mainRide;
    }

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("user_id")
    @Expose
    private int userId;

    @SerializedName("plate_number")
    @Expose
    private String plateNumber;

    @SerializedName("make_model")
    @Expose
    private String makeModel;

    @SerializedName("main_ride")
    @Expose
    boolean mainRide;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public boolean getMainRide() {
        return mainRide;
    }

    public void setMainRide(boolean mainRide) {
        this.mainRide = mainRide;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}