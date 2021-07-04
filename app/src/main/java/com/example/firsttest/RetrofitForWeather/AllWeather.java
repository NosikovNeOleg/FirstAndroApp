package com.example.firsttest.RetrofitForWeather;


import com.google.gson.annotations.SerializedName;

import java.util.List;


public class AllWeather {

    @SerializedName("weather")
    private List<Weather> weather;


    @SerializedName("main")
    private Main main;


}



