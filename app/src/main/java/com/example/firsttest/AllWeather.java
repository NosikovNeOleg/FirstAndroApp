package com.example.firsttest;


import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;


public class AllWeather {

    @SerializedName("weather")
    private List<Weather> weather;



    @SerializedName("main")
    private Main main;


}



