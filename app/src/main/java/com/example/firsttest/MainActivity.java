package com.example.firsttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.firsttest.CustomView.CustomViewFreeDots;
import com.example.firsttest.db.WeatherDB;
import com.example.firsttest.getWeatherToActivity.GetAndSetWeather;
import com.example.firsttest.getWeatherToActivity.RetrofitService;
import com.google.gson.Gson;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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


        AboutMe.setOnClickListener(new View.OnClickListener()                   //переключение на активити с информацией о разработчике
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
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
                    new GetAndSetWeather(resultWeather, DeskWeather, constraintLayout, city);
                    buttonForClothes.setVisibility(View.VISIBLE);
                }

            }

/*          База данных
                            Runnable runnable = new Runnable() {
                                @Override
                                public void run() {
                                    WeatherDB db = Room.databaseBuilder(getApplicationContext(),
                                            WeatherDB.class, "populus-database").build();

                                    WeatherRoom model = new WeatherRoom();
                                    model.setWeatherToDB(resultWeather.getText().toString);
                                    model.setDescriptionOfWeatherToDB(DeskWeather.getText().toString);
                                    //db.getDataDao().insertWeather(model);      //добавить в бд
                                    //db.getDataDao().deleteWeather(model);        // удалить из бд
                                    Log.d("this_is_tag", String.valueOf(new Gson().toJson(db.getDataDao().getAllWeather())));
                                }

                                };
                            Thread thread = new Thread(runnable);
                            thread.start();
*/
            });

                    }

                }



















