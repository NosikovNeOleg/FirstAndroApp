package com.example.firsttest.RetrofitTaskTwo;

import com.google.gson.annotations.SerializedName;

public class DataRes {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("year")
    private int year;

    @SerializedName("color")
    private String color;

    @SerializedName("pantone_value")
    private String pantone_value;
}
