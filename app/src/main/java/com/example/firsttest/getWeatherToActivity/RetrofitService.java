package com.example.firsttest.getWeatherToActivity;

import com.example.firsttest.RetrofitForWeather.AllWeather;
import com.example.firsttest.RetrofitTaskTwo.AddUserName;
import com.example.firsttest.RetrofitTaskTwo.PatchInfo;
import com.example.firsttest.RetrofitTaskTwo.Resources;
import com.example.firsttest.RetrofitTaskTwo.UnoResource;
import com.example.firsttest.RetrofitTaskTwo.UpdateInfo;
import com.example.firsttest.RetrofitTaskTwo.User;
import com.example.firsttest.RetrofitTaskTwo.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface RetrofitService {


    @GET("weather?")
    Call<AllWeather> weather(
            //@Query("city") String city,
            @Query("q") String city,
            @Query("lang") String lang,
            @Query("units") String units,
            @Query("appid") String api
    );

    // для домашнего задания
    @GET("users?page=2")
    Call<Users> getListUsers();

    @GET("users/2")
    Call<User> getUser();

    @GET("users/23")
    Call<User> getUser404();

    @GET("unknown")
    Call<Resources> getRes();

    @GET("unknown/2")
    Call<UnoResource> getOneRes();

    @GET("unknown/23")
    Call<UnoResource> getOneRes404();

    @POST("users")
    Call<AddUserName> addUser(@Body AddUserName body);

    @PUT("users/2")
    Call<UpdateInfo> updateInfo(@Body UpdateInfo body);

    @PATCH("users/2")
    Call<PatchInfo> PatchInfo(@Body UpdateInfo body);

    @DELETE("users/2")
    Call<Users> deleteAll();
}
