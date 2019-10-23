package com.example.carpark.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharePreference {
    private static final String ID_KEY="com.example.carpark_ID_KEY";

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


}
