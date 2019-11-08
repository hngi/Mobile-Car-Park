package com.carpark.Model.Park;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 31/10/19
 */

public class ParkAddress {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("address_id")
    @Expose
    private int address_id;
    @SerializedName("park_name")
    @Expose
    private String park_name;
    @SerializedName("park_address")
    @Expose
    private String park_address;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getPark_name() {
        return park_name;
    }

    public void setPark_name(String park_name) {
        this.park_name = park_name;
    }

    public String getPark_address() {
        return park_address;
    }

    public void setPark_address(String park_address) {
        this.park_address = park_address;
    }

}