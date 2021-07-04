package com.example.firsttest.RetrofitTaskTwo;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    int id;

    @SerializedName("email")
    String email;

    @SerializedName("first_name")
    String first_name;

    @SerializedName("last_name")
    String last_name;
}
