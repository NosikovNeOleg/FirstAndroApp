package com.example.firsttest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    /*
     * https://api.openweathermap.org/data/2.5/weather?q=Perm&lang=ru&units=metric&appid=ea6f0e578383b90c05d2a363c4e39c3e
     * */

    @GET("weather?q=Perm&lang=ru&units=metric&appid=ea6f0e578383b90c05d2a363c4e39c3e")
    Call<AllWeather> weather(
            //@Query("city") String city           //паф почему-то пока не работает
            //@Path("api") String api
    );



}
