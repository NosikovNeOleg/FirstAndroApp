package com.example.firsttest.RetrofitTaskTwo;

import com.google.gson.annotations.SerializedName;

public class AddUserName {
    @SerializedName("name")
    String name = "morpheus";
    @SerializedName("job")
    String job = "leader";
    @SerializedName("id")
    String id;
    @SerializedName("createdAt")
    String createdAt;

    public String getName() {
        return name;
    }
    public String getJob() {
        return job;
    }
}
