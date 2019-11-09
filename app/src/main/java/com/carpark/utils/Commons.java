package com.carpark.utils;

import android.app.Application;

import com.carpark.Model.User;


/**
 * @author .: Ehma Ugbogo
 * @email ..: ehmaugbogo@gmail.com
 * @created : 06/11/19
 */

public class Commons extends Application {
    private static User storedUser;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void setUser(User user) {
        storedUser = user;
    }

    public static User getUser() {
        return storedUser;
    }
}
