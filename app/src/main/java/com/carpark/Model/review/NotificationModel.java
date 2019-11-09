package com.carpark.Model.review;

public class NotificationModel {
    private  String message;
    private Integer imageUrl;
    private String timeStamp;

    public NotificationModel(String message, Integer imageUrl, String timeStamp) {
        this.message = message;
        this.imageUrl = imageUrl;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
