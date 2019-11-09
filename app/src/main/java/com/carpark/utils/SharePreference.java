package com.carpark.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePreference {
    private static final String ID_KEY="com.carpark_ID_KEY";
    private static final String ID_ACCESS_KEY="com.carpark_ID_ACCESS_KEY";
    private static final String ID_EXPIRE_KEY="com.carpark_ID_EXPIRE_KEY";
    private static final String ID_LOGGED_IN_KEY="com.carpark_ID_LOGGED_IN_KEY";
    private static final String VEHICLE_NAME="com.carpark_VEHICLE_NAME";
    private static final String VEHICLE_ID="com.carpark_VEHICLE_ID";
    private static final String VEHICLE_NUMBER="com.carpark_VEHICLE_NUMBER";
    private static final String FORMATTED_DAY="com.carpark_FORMATTED_DAY";
    private static final String FORMATTED_TIME="com.carpark_FORMATTED_TIME";
    private static final String FORMATTED_DAY_OUT="com.carpark_FORMATTED_DAY_OUT";
    private static final String FORMATTED_TIME_OUT="com.carpark_FORMATTED_TIME_OUT";
    private static final String FORMATTED_DATE_IN="com.carpark_FORMATTED_DATE_IN";
    private static final String FORMATTED_DATE_OUT="com.carpark_FORMATTED_DATE_OUT";
    private static final String CHECK_IN="com.carpark_CHECK_IN";
    private static final String CHECK_OUT="com.carpark_CHECK_OUT";
    private static final String DURATION="com.carpark_DURATION";






    private static SharePreference INSTANCE;

    public static synchronized SharePreference getINSTANCE(Context context) {
        if(INSTANCE==null){
            //noinspection deprecation
            INSTANCE = new SharePreference(PreferenceManager.getDefaultSharedPreferences(context));
        }
        return INSTANCE;
    }



    private SharedPreferences sharedPreferences;

    private SharePreference(SharedPreferences sharedPreferences) {
        //this.sharedPreferences = context.getSharedPreferences("SharedPref",Context.MODE_PRIVATE);
        this.sharedPreferences=sharedPreferences;

    }

    public void setLoggedUserId(Long id){
        sharedPreferences.edit().putLong(ID_KEY,id).apply();
    }

    public Long getLoggedUserId(){
        return sharedPreferences.getLong(ID_KEY,-1);
    }

    public void setAccesstoken(String accesstoken){
        String fullToken = "Bearer " + accesstoken;
        sharedPreferences.edit().putString(ID_ACCESS_KEY, fullToken).apply();
    }

    public String getAccessToken(){
        return sharedPreferences.getString(ID_ACCESS_KEY, "null");
    }

    public void setExpiresIn(int expiresIn){
        sharedPreferences.edit().putInt(ID_EXPIRE_KEY,expiresIn).apply();
    }

    public int getExpiresIn(){
        return sharedPreferences.getInt(ID_EXPIRE_KEY, -1);
    }

    public void setIsUserLoggedIn(boolean isUserLoggedIn){
        sharedPreferences.edit().putBoolean(ID_LOGGED_IN_KEY,isUserLoggedIn).apply();
    }

    public boolean getIsUserLoggedIn(){
        return sharedPreferences.getBoolean(ID_LOGGED_IN_KEY, false);
    }
    public void setMainVehicleName(String vehicleName){
        sharedPreferences.edit().putString(VEHICLE_NAME,vehicleName).apply();
    }

    public String getMainVehicleName(){
        return sharedPreferences.getString(VEHICLE_NAME, "_____");
    }
    public void setMainVehicleNumber(String vehicleNumber){
        sharedPreferences.edit().putString(VEHICLE_NUMBER,vehicleNumber).apply();
    }
    public String getMainVehicleNumber(){
        return sharedPreferences.getString(VEHICLE_NUMBER, "_____");
    }

    public void setMainVehicleId(int vehicleId){
        sharedPreferences.edit().putInt(VEHICLE_ID,vehicleId).apply();
    }
    public int getMainVehicleId(){
        return sharedPreferences.getInt(VEHICLE_ID, 0);
    }

    public void setINFormattedTime(String formattedTime){
        sharedPreferences.edit().putString(FORMATTED_TIME,formattedTime).apply();
    }
    public String getINFormattedTime(){
        return sharedPreferences.getString(FORMATTED_TIME, "-----");
    }

    public void setINFormattedDay(String formattedDay){
        sharedPreferences.edit().putString(FORMATTED_DAY,formattedDay).apply();
    }
    public String getINFormattedDay(){
        return sharedPreferences.getString(FORMATTED_DAY, "-----");
    }

    public void setOutFormattedTime(String formattedTime){
        sharedPreferences.edit().putString(FORMATTED_TIME_OUT,formattedTime).apply();
    }
    public String getOutFormattedTime(){
        return sharedPreferences.getString(FORMATTED_TIME_OUT, "-----");
    }

    public void setOutFormattedDay(String formattedDay){
        sharedPreferences.edit().putString(FORMATTED_DAY_OUT,formattedDay).apply();
    }
    public String getOutFormattedDay(){
        return sharedPreferences.getString(FORMATTED_DAY_OUT, "-----");
    }
    public void setOutFormattedDate(String formattedDay){
        sharedPreferences.edit().putString(FORMATTED_DATE_OUT,formattedDay).apply();
    }
    public String getOutFormattedDate(){
        return sharedPreferences.getString(FORMATTED_DATE_OUT, "-----");
    }
    public void setINFormattedDate(String formattedDay){
        sharedPreferences.edit().putString(FORMATTED_DATE_IN,formattedDay).apply();
    }
    public String getINFormattedDate(){
        return sharedPreferences.getString(FORMATTED_DATE_IN, "-----");
    }


    public void setCheckOut(String mCheckOut) {
        sharedPreferences.edit().putString(CHECK_OUT,mCheckOut).apply();
    }
    public String getCheckOut(){
        return sharedPreferences.getString(CHECK_OUT, "-----");
    }
    public void setCheckIn(String mCheckOut) {
        sharedPreferences.edit().putString(CHECK_IN,mCheckOut).apply();
    }
    public String getCheckIn(){
        return sharedPreferences.getString(CHECK_IN, "-----");
    }
    public void setDuration(String duration) {
        sharedPreferences.edit().putString(DURATION,duration).apply();
    }
    public String getDuration(){
        return sharedPreferences.getString(DURATION, "-----");
    }

}
