package com.example.firsttest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    /*
     * https://api.openweathermap.org/data/2.5/weather?q=Perm&lang=ru&units=metric&appid=ea6f0e578383b90c05d2a363c4e39c3e
     * */

    @GET("weather?q=Perm&lang=ru&units=metric&appid=ea6f0e578383b90c05d2a363c4e39c3e")
    Call<AboutWeather> weather(
            //@Path("city") String city,           //паф почему-то пока не работает
            //@Path("api") String api
    );



}
