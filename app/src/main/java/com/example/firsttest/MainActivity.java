package com.example.firsttest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONException;
import org.json.JSONObject;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextTextCity;
    private Button buttonForWeather;
    private TextView resultWeather;
    private TextView bg;


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
        bg = findViewById(R.id.bg);



        buttonForWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextTextCity.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, R.string.alertCity, Toast.LENGTH_LONG).show();
                }
                else{
                    String city = editTextTextCity.getText().toString();
                    String api_key = "ea6f0e578383b90c05d2a363c4e39c3e";
                    String url = "https://api.openweathermap.org/data/2.5/weather?q="+ city + "&appid=" + api_key;

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
                    JSONObject jsonText = new JSONObject(result);
                    String weather = (("" +jsonText.getJSONObject("main").getDouble("temp")).substring(0, 2));
                    double check = jsonText.getJSONObject("main").getDouble("temp");
                    if (weather.substring(0,1) != "-"){
                        weather = "+" + weather;
                    }
                    resultWeather.setText(weather);
                    resultWeather.setTextSize(100);
                    if (check > 25){

                        Drawable draw = getResources().getDrawable(R.drawable.bg_hot);
                        bg.setBackground(draw);
                    }
                    if (check < 25){
                        if(check > 15) {
                            Drawable draw = getResources().getDrawable(R.drawable.bg_neutral);
                            bg.setBackground(draw);
                        }
                    }
                    if (check < -15){
                        Drawable draw = getResources().getDrawable(R.drawable.bg_cold);
                        bg.setBackground(draw);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }
    public void onClick(View view) {
        Intent intent = new Intent(this, ClothesActivity.class);
        CharSequence weather = resultWeather.getText();
        intent.putExtra("weather", weather);
        startActivity(intent);
    }


    }



