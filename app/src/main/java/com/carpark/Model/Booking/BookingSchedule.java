package com.carpark.Model.Booking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 09/10/19
 */

public class BookingSchedule {

    @SerializedName("check_in")
    @Expose
    private String checkIn;
    @SerializedName("check_out")
    @Expose
    private String checkOut;
    @SerializedName("vehicle_no")
    @Expose
    private String vehicleNo;


    public BookingSchedule() {
        //No args constructor for use in serialization
    }

    public BookingSchedule(String checkIn, String checkOut, String vehicleNo) {
        super();
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.vehicleNo = vehicleNo;
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

}