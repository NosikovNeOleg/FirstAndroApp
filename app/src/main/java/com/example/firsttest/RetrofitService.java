package com.example.firsttest;

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


public interface RetrofitService {

    /*
     * https://api.openweathermap.org/data/2.5/weather?q=Perm&lang=ru&units=metric&appid=ea6f0e578383b90c05d2a363c4e39c3e
     * */

    @GET("weather?q=Perm&lang=ru&units=metric&appid=ea6f0e578383b90c05d2a363c4e39c3e") // добавить пользовательский ввод города
    Call<AllWeather> weather(
            //@Query("city") String city           //паф почему-то пока не работает
            //@Path("api") String api
    );

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
