package com.carpark.Model.review;

public class ParkingHistoryModel {
    private String parkingHistoryDate;
    private String parkingHistoryTime;
    private String location;
    private String qrCode;
    private String amount;


    public ParkingHistoryModel(String parkingHistoryDate, String parkingHistoryTime, String location, String qrCode, String amount){
        this.parkingHistoryDate = parkingHistoryDate;
        this.parkingHistoryTime = parkingHistoryTime;
        this.location = location;
        this.qrCode = qrCode;
        this.amount = amount;
    }

    public void setParkingHistoryDate(String parkingHistoryDate){
        this.parkingHistoryDate = parkingHistoryDate;
    }

    public String getParkingHistoryDate(){
        return parkingHistoryDate;
    }

    public void setParkingHistoryTime(String parkingHistoryTime){
        this.parkingHistoryTime = parkingHistoryTime;
    }

    public String getParkingHistoryTime(){
        return parkingHistoryTime;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getLocation(){
        return location;
    }

    public void setQrCode(String qrCode){
        this.qrCode = qrCode;
    }

    public String getQrCode(){
        return qrCode;
    }

    public void setAmount(String amount){
        this.amount = amount;
    }

    public String getAmount(){
        return amount;
    }

}
