package com.carpark.Model.Park;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 09/10/19
 */

public class UserPackedSpace {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("car_park_id")
    @Expose
    private int carParkId;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("check_in")
    @Expose
    private String checkIn;
    @SerializedName("check_out")
    @Expose
    private String checkOut;
    @SerializedName("vehicle_no")
    @Expose
    private String vehicleNo;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("car_park_booking_id")
    @Expose
    private int carParkBookingId;
    @SerializedName("history_count")
    @Expose
    private int historyCount;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("fee")
    @Expose
    private int fee;
    @SerializedName("image_link")
    @Expose
    private Object imageLink;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarParkId() {
        return carParkId;
    }

    public void setCarParkId(int carParkId) {
        this.carParkId = carParkId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getCarParkBookingId() {
        return carParkBookingId;
    }

    public void setCarParkBookingId(int carParkBookingId) {
        this.carParkBookingId = carParkBookingId;
    }

    public int getHistoryCount() {
        return historyCount;
    }

    public void setHistoryCount(int historyCount) {
        this.historyCount = historyCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Object getImageLink() {
        return imageLink;
    }

    public void setImageLink(Object imageLink) {
        this.imageLink = imageLink;
    }
}
