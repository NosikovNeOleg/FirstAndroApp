package com.example.firsttest.getWeatherToActivity;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Pair;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.firsttest.MainActivity;
import com.example.firsttest.R;
import com.example.firsttest.RetrofitForWeather.AllWeather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAndSetWeather {


    private String resultWeather;
    private String DeskWeather;
    private ConstraintLayout constraintLayout;


    public GetAndSetWeather(TextView resultWeather, TextView deskWeather, ConstraintLayout constraintLayout, String city) {
                RetrofitService service = new InitRetrofit().service;
                service.weather(city,"ru","metric","ea6f0e578383b90c05d2a363c4e39c3e").enqueue(new Callback<AllWeather>() {
                    @Override
                    public void onResponse(Call<AllWeather> call, Response<AllWeather> response) {
                        response.body();
                        try {
                            JSONObject result = new JSONObject(new Gson().toJson(response.body()));
                            JSONObject resultWeatherFromJson = result.getJSONObject("main");
                            String toView = resultWeatherFromJson.getString("temp");
                            toView = toView.substring(0, toView.indexOf("."));
                            int check = Integer.valueOf(toView);
                            if (toView.subSequence(0,1) != "-"){
                                resultWeather.setText("+" + toView);}

                            if (check >= 25){
                                constraintLayout.setBackgroundResource(R.drawable.bg_hot);
                            }

                            else if (check < 25 && check >= 5) {
                                constraintLayout.setBackgroundResource(R.drawable.bg_neutral);
                            }
                            if (check < 5){
                                constraintLayout.setBackgroundResource(R.drawable.bg_cold);;
                            }

                            JSONArray resultDescription = result.getJSONArray("weather");
                            JSONObject resultDescription2 = resultDescription.getJSONObject(0);
                            String Description = resultDescription2.getString("description");
                            deskWeather.setText(Description);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("this_is_tag", "Ошибка в GetWeather в json");
                            resultWeather.setText("");
                            deskWeather.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<AllWeather> call, Throwable t) {
                        Log.d("this_is_tag", "Ошибка в GetWeather");
                    }
                }
                );
            }
        };
