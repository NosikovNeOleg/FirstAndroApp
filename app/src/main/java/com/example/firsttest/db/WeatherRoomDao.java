package com.example.firsttest.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeatherRoomDao {

    @Insert
    void insertWeather(WeatherRoom weatherRoom);

    @Delete
    void deleteWeather(WeatherRoom weatherRoom);

    @Query("SELECT * FROM weatherRoom")
    List<WeatherRoom> getAllWeather();
}
