package com.example.firsttest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;



import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextTextCity;
    private TextView AboutMe;
    private TextView resultWeather;
    private TextView DeskWeather;
    private Button button;
    private Button buttonForWeather;
    private Button buttonForMore;
    private ConstraintLayout constraintLayout;

     private void initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new IntEx())
                .build();

        Retrofit retrofit = (new Retrofit.Builder())
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(client)
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);


         service.weather(/*"perm","ea6f0e578383b90c05d2a363c4e39c3e"*/).enqueue(new Callback<AboutWeather>() {
            @Override
            public void onResponse(Call<AboutWeather> call, Response<AboutWeather> response) {
                response.body();
                Log.d("this_is_tag", "Вызов");
            }
            @Override
            public void onFailure(Call<AboutWeather> call, Throwable t) {
                Log.d(getClass().toString(), t.getMessage());
            }

        });


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonBack = (Button)findViewById(R.id.button);
        buttonBack.setOnClickListener(this);
        overridePendingTransition(0,0);

        editTextTextCity = findViewById(R.id.editTextTextCity);
        buttonForWeather = findViewById(R.id.buttonForWeather);
        resultWeather = findViewById(R.id.resultWeather);
        DeskWeather = findViewById(R.id.DeskWeather);
        button = findViewById(R.id.button);
        constraintLayout = findViewById(R.id.constraintLayout);
        AboutMe = findViewById(R.id.AboutMe);
        buttonForMore = findViewById(R.id.buttonForMore);






        AboutMe.setOnClickListener(new View.OnClickListener()                   //переключение на активити с информацией о разработчике
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutMe.class);
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
                if(editTextTextCity.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, R.string.alertCity, Toast.LENGTH_LONG).show();
                    initRetrofit();
                }
                else{
                    String city = editTextTextCity.getText().toString();
                    String api_key = "ea6f0e578383b90c05d2a363c4e39c3e";
                    String url = "https://api.openweathermap.org/data/2.5/weather?q="+ city + "&lang=ru&units=metric&appid=" + api_key;
                    new GetWeather().execute(url);


                }
            }
        });



    }



    private class GetWeather extends AsyncTask<String, String, String>{

        protected void onPreExecute() {
            super.onPreExecute();
            resultWeather.setTextSize(24);
            resultWeather.setText("Получение данных");

        }
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connect = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connect = (HttpURLConnection) url.openConnection();
                connect.connect();

                InputStream stream = connect.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String text = null;
                while((text = reader.readLine())!= null)
                    buffer.append(text).append("\n");
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } finally {
                if(connect != null){
                    connect.disconnect();
                }
                try {
                if(reader != null){
                        reader.close();
                    }
                } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            return null;
            }

            @SuppressLint("SetTextI18n")
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                try {
                    JSONObject jsonText = new JSONObject(result);       //здесь нужно будет достать больше данных
                    String weather = (("" + jsonText.getJSONObject("main").getDouble("temp")).substring(0, 2));
                    String description = jsonText.getJSONArray("weather").getString(0);
                    int fromDesk1 = description.indexOf("description");
                    int fromDesk2 = description.indexOf("icon");
                    description = description.substring(fromDesk1 + 14,fromDesk2 - 3);
                    double check = jsonText.getJSONObject("main").getDouble("temp");

                    if (weather.substring(0,1) != "-"){
                        weather = "+" + weather;
                    }
                    resultWeather.setText(weather);
                    resultWeather.setTextSize(100);
                    DeskWeather.setText(description);
                    button.setVisibility(View.VISIBLE);
                    buttonForMore.setVisibility(View.VISIBLE);
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
                }



            }
        }
    public void onClick(View view) {
        Intent intent = new Intent(this, ClothesActivity.class);    //кнопка переключения на активити с одеждой
        CharSequence weather = resultWeather.getText();
        intent.putExtra("weather", weather);
        startActivity(intent);
    }

};







