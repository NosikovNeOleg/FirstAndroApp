package com.example.firsttest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClothesActivity extends MainActivity  {

    private TextView temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        Bundle arguments = getIntent().getExtras();
        temp = findViewById(R.id.temp);
        if(arguments != null){
            String weather_to_show = arguments.get("weather").toString();
            temp.setText(weather_to_show);
            int temperature = new Integer(weather_to_show);
            if (temperature > 25){

            }
        }
        if(arguments == null){
            temp.setText("-");
        }


    }

}
