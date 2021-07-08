package com.example.firsttest.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity
public class WeatherRoom {

    @PrimaryKey(autoGenerate = true)
    int idRoom;

    @ColumnInfo(name = "city")
    String city;

    @ColumnInfo(name = "weather")
    int weather;

    @ColumnInfo(name = "description")
    String DescriptionOfWeather;

    @ColumnInfo(name = "date")
    String momentOfWeather;

    @NonNull
    public int getIdRoom() {
        return idRoom;
    }



    @NonNull
    public int getWeather() {
        return weather;
    }

    public void setWeatherToDB(@NonNull Integer weather) {
        this.weather = weather;
    }



    @NonNull
    public String getDescription() {
        return DescriptionOfWeather;
    }

    public void setDescriptionOfWeatherToDB(@NonNull String DescriptionOfWeather) {
        this.DescriptionOfWeather = DescriptionOfWeather;
    }

    // добавить ещё сеттеров и геттеров
}

