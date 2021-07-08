package com.example.firsttest;

import android.app.Application;

import androidx.room.Room;

import com.example.firsttest.db.WeatherDB;

public class App extends Application {

    private static App instance;
    private WeatherDB db;

    public static App getInstance() {

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), WeatherDB.class, "database_w")
                .allowMainThreadQueries()
                .build();
    }

    public WeatherDB getDatabaseInstance() {

        return db;
    }
}