package com.example.carpark.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private final LiveData<List<User>> solarCalList;
    private UserDao userDao;


    public Repository(Context context) {
        UserDatabase userDatabase = UserDatabase.getInstance(context);
        userDao = userDatabase.userDao();
        userDao = userDatabase.userDao();
        solarCalList = (LiveData<List<User>>) userDao.getAllUsers();
    }

    //Getters for User
    public Long insertUser(User user){
        return userDao.insertUser(user);
    }

    public void updateUser(User user){
        userDao.updateUser(user);
    }

    public void deleteUser(User user){
        userDao.delateUser(user);
    }

    public List<User> getallUsers(){
        return (List<User>) userDao.getAllUsers();
    }
}
