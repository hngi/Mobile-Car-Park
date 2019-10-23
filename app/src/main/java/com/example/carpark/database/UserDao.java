package com.example.carpark.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UserDao {

    @Insert
    Long insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void delateUser(User user);

   @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();
}
