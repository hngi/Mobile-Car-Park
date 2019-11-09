package com.carpark.Api.Responses.Park;
import java.util.List;

import com.carpark.Model.Park.UserPackedSpace;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 09/10/19
 */

public class ParkingSpaceHistoryResponse {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("result")
    @Expose
    private List<UserPackedSpace> result = null;

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

    public List<UserPackedSpace> getResult() {
        return result;
    }

    public void setResult(List<UserPackedSpace> result) {
        this.result = result;
    }

}