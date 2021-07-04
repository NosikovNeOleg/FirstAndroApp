package com.example.firsttest.RetrofitTaskTwo;

import com.google.gson.annotations.SerializedName;


public class UpdateInfo {
        @SerializedName("name")
        String name = "morpheus";
        @SerializedName("job")
        String job = "zion resident";
        @SerializedName("updatedAt")
        String updatedAt;


        public String getJob() {
            return job;
        }
    }

