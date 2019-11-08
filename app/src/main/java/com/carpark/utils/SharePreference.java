package com.carpark.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePreference {
    private static final String ID_KEY="com.carpark_ID_KEY";
    private static final String ID_ACCESS_KEY="com.carpark_ID_ACCESS_KEY";
    private static final String ID_EXPIRE_KEY="com.carpark_ID_EXPIRE_KEY";
    private static final String ID_LOGGED_IN_KEY="com.carpark_ID_LOGGED_IN_KEY";



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


}
