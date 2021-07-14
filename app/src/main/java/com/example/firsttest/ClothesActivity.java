package com.example.firsttest;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ClothesActivity extends MainActivity  {

    private TextView temp;
    private TextView clothes;
    private ConstraintLayout ClothesLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);
        Bundle arguments = getIntent().getExtras();
        temp = findViewById(R.id.temp);
        clothes = findViewById(R.id.clothes);
        ClothesLayout = findViewById(R.id.ClothesLayout);

        if(arguments != null){
            String weather_to_show = arguments.get("weather").toString();
            temp.setText(weather_to_show);
            double temperature = new Integer(weather_to_show);
            clothes.setVisibility(View.VISIBLE);
            if (temperature >= 25){
                clothes.setText("Сегодня очень жарко, желательно надеть что-то легкое и светлых тонов");
                Drawable draw = getResources().getDrawable(R.drawable.bg_hot);
                ClothesLayout.setBackground(draw);
            }
            else if (temperature < 25 && temperature >= 18){
                clothes.setText("Сегодня жарковато, можно надеть футболку и легкие штаны");
                Drawable draw = getResources().getDrawable(R.drawable.bg_neutral);
                ClothesLayout.setBackground(draw);
            }
            else if (temperature < 18 && temperature >= 5){
                clothes.setText("Комфортная температура, чтобы надеть ветровку или кофту");
                Drawable draw = getResources().getDrawable(R.drawable.bg_neutral);
                ClothesLayout.setBackground(draw);
            }
            else if (temperature < 5 && temperature >= -5){
                clothes.setText("Сегодня можно надеть пальто или демисезонную куртку");
                Drawable draw = getResources().getDrawable(R.drawable.bg_cold);
                ClothesLayout.setBackground(draw);
            }
            else if (temperature < -5 && temperature >= -15){
                clothes.setText("Холодновато, можете надеть вашу лучшую куртку, также не забудьте шапку!");
                Drawable draw = getResources().getDrawable(R.drawable.bg_cold);
                ClothesLayout.setBackground(draw);
            }
            else if (temperature < -15 ){
                clothes.setText("На улице очень холодно, наденьте зимнюю куртку, шапку и, желательно, варежки");
                Drawable draw = getResources().getDrawable(R.drawable.bg_cold);
                ClothesLayout.setBackground(draw);
            }
        }
        if(arguments == null){
            temp.setText("...");
            clothes.setText("Узнайте температуру");
        }


    }

}
