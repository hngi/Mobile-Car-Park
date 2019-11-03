package com.example.carpark.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParkingSpace implements Parcelable
{

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

    /**
     * No args constructor for use in serialization
     *
     */
    public ParkingSpace() {
    }

    /**
     *
     * @param owner
     * @param address
     * @param phone
     * @param fee
     * @param name
     */
    public ParkingSpace(String name, String owner, String address, String phone, int fee) {
        super();
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.phone = phone;
        this.fee = fee;
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

    public final static Parcelable.Creator<ParkingSpace> CREATOR = new Creator<ParkingSpace>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ParkingSpace createFromParcel(Parcel in) {
            return new ParkingSpace(in);
        }

        public ParkingSpace[] newArray(int size) {
            return (new ParkingSpace[size]);
        }

    }
            ;

    protected ParkingSpace(Parcel in) {
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.owner = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.phone = ((String) in.readValue((String.class.getClassLoader())));
        this.fee = ((int) in.readValue((int.class.getClassLoader())));
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(owner);
        dest.writeValue(address);
        dest.writeValue(phone);
        dest.writeValue(fee);
    }

    public int describeContents() {
        return 0;
    }

}