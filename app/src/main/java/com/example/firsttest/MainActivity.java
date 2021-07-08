package com.example.firsttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.firsttest.CustomView.CustomViewFreeDots;
import com.example.firsttest.RetrofitForWeather.AllWeather;
import com.example.firsttest.db.WeatherDB;
import com.example.firsttest.db.WeatherRoom;
import com.example.firsttest.db.WeatherRoomDao;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //////////////////////////////////////////room db
    private WeatherDB WeatherDB;
    /////////////////////////////////////////

    private EditText editTextTextCity;
    private CustomViewFreeDots AboutMe;
    private TextView resultWeather;
    private TextView DeskWeather;
    private Button buttonForClothes;
    private Button buttonForWeather;
    private Button buttonForMore;
    private ConstraintLayout constraintLayout;
    private Button buttonHomeTask;

    public RetrofitService initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new IntEx())
                .build();

        Retrofit retrofit = (new Retrofit.Builder())
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        return service;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(0, 0);

//////////
        WeatherDB = App.getInstance().getDatabaseInstance();

        /////////////////

        editTextTextCity = findViewById(R.id.editTextTextCity);
        buttonForWeather = findViewById(R.id.buttonForWeather);
        resultWeather = findViewById(R.id.resultWeather);
        DeskWeather = findViewById(R.id.DeskWeather);
        constraintLayout = findViewById(R.id.constraintLayout);
        AboutMe = findViewById(R.id.AboutMe);
        buttonForMore = findViewById(R.id.buttonForMore);
        buttonHomeTask = findViewById(R.id.buttonHomeTask);
        buttonForClothes = findViewById(R.id.buttonForClothes);



        buttonForClothes.setOnClickListener(new View.OnClickListener() {                //кнопка переключения на активити с одеждой
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClothesActivity.class);
                String weather = (String) resultWeather.getText();
                intent.putExtra("weather", weather);
                startActivity(intent);
            }
        });

        buttonHomeTask.setOnClickListener(new View.OnClickListener()                   //переключение на активити с задачами по ретрофиту
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RetrofitTaskTwoActivity.class);
                startActivity(intent);

            }
        });


        AboutMe.setOnClickListener(new View.OnClickListener()                   //переключение на активити с задачами по ретрофиту
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);

            }
        });


        buttonForMore.setOnClickListener(new View.OnClickListener()              //переключение на активити с подробной погодой
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MoreWeather.class);
                // здесь передавать в активити подробные данные из апи
                startActivity(intent);

            }
        });


        buttonForWeather.setOnClickListener(new View.OnClickListener()          //кнопка для погоды
        {
            @Override
            public void onClick(View view) {
                if (editTextTextCity.getText().toString().trim().equals("")) {
                    Toast.makeText(MainActivity.this, R.string.alertCity, Toast.LENGTH_SHORT).show();

                } else {
                    String city = editTextTextCity.getText().toString();
                    RetrofitService service = initRetrofit();
                    service.weather(/*city,ea6f0e578383b90c05d2a363c4e39c3e"*/).enqueue(new Callback<AllWeather>() {
                        @Override
                        public void onResponse(Call<AllWeather> call, Response<AllWeather> response) {
                            response.body();
                            try {
                                JSONObject result = new JSONObject(new Gson().toJson(response.body()));
                                JSONObject resultWeatherFromJson = result.getJSONObject("main");
                                String toView = resultWeatherFromJson.getString("temp");
                                toView = toView.substring(0,toView.indexOf("."));

                                JSONArray resultDescription = result.getJSONArray("weather");
                                JSONObject resultDescription2 = resultDescription.getJSONObject(0);
                                String Description = resultDescription2.getString("description");


                                DeskWeather.setText(Description);
                                buttonForClothes.setVisibility(View.VISIBLE);
                                buttonForMore.setVisibility(View.VISIBLE);

                                if (toView.substring(0,1) != "-"){
                                    toView = "+" + toView;
                                }
                                resultWeather.setText(toView) ;

                                double check = Double.valueOf(toView) ;
                                if (check > 25){
                                    Drawable draw = getResources().getDrawable(R.drawable.bg_hot);
                                    constraintLayout.setBackground(draw);
                                }
                                else if (check < 25 | check > 5) {
                                    Drawable draw = getResources().getDrawable(R.drawable.bg_neutral);
                                    constraintLayout.setBackground(draw);
                                }
                                if (check < 5){
                                    Drawable draw = getResources().getDrawable(R.drawable.bg_cold);
                                    constraintLayout.setBackground(draw);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            };


                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    WeatherDB db = Room.databaseBuilder(getApplicationContext(),
                                            WeatherDB.class, "populus-database").build();

                                    WeatherRoom model = new WeatherRoom();
                                    model.setWeatherToDB(32);                     // здесь брать погоду из респонса
                                    model.setDescriptionOfWeatherToDB("Ясно");
                                    //db.getDataDao().insertWeather(model);      //добавить в бд
                                    //db.getDataDao().deleteWeather(model);        // удалить из бд
                                    Log.d("this_is_tag", String.valueOf(new Gson().toJson(db.getDataDao().getAllWeather())));
                                }

                                };
                            Thread thread = new Thread(runnable);               // предполагаю, что каждый раз создаётся новый поток
                            thread.start();

                            }
                            @Override
                        public void onFailure(Call<AllWeather> call, Throwable t) {
                            Log.d(getClass().toString(), t.getMessage());
                            Log.d("this_is_tag", "Ошибка");
                        }

                    });

                }

            }

        });
        /////////////////////////////////////////////////////////////////////////////////////////кнопки закончились



    }





}











