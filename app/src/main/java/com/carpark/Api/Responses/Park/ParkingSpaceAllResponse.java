package com.carpark.Api.Responses.Park;

import com.carpark.Model.Park.ParkingSpace;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 03/10/19
 */

public class ParkingSpaceAllResponse {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("result")
    @Expose
    private List<ParkingSpace> parkingSpaces = null;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ParkingSpace> getParkingSpaces() {
        return parkingSpaces;
    }

    public void setParkingSpaces(List<ParkingSpace> parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }
}