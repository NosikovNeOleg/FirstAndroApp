package com.example.firsttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.firsttest.RetrofitTaskTwo.AddUserName;
import com.example.firsttest.RetrofitTaskTwo.PatchInfo;
import com.example.firsttest.RetrofitTaskTwo.Resources;
import com.example.firsttest.RetrofitTaskTwo.UnoResource;
import com.example.firsttest.RetrofitTaskTwo.UpdateInfo;
import com.example.firsttest.RetrofitTaskTwo.User;
import com.example.firsttest.RetrofitTaskTwo.Users;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTaskTwoActivity extends AppCompatActivity {

    public RetrofitService initRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new IntEx())
                .build();

        Retrofit retrofit = (new Retrofit.Builder())
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);
        return service;
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_task_two);

        Button button_first;
        Button button_second;
        Button button_third;
        Button button_fourth;
        Button button_five;
        Button button_six;
        Button button_seven;
        Button button_eight;
        Button button_nine;
        Button button_ten;
        TextView textView_retrofit;


        button_first = findViewById(R.id.button_first);
        button_second = findViewById(R.id.button_second);
        button_third = findViewById(R.id.button_third);
        button_fourth = findViewById(R.id.button_fourth);
        button_five = findViewById(R.id.button_five);
        button_six = findViewById(R.id.button_six);
        button_seven = findViewById(R.id.button_seven);
        button_eight = findViewById(R.id.button_eight);
        button_nine = findViewById(R.id.button_nine);
        button_ten = findViewById(R.id.button_ten);
        textView_retrofit = findViewById(R.id.textView_retrofit);


        button_first.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                RetrofitService service = initRetrofit();
                service.getListUsers().enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        response.body();
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
            });
            }});


        button_second.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                RetrofitService service = initRetrofit();
                service.getUser().enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        response.body();
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
                });
            }


        });


        button_third.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                RetrofitService service = initRetrofit();
                service.getUser404().enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        response.body();
                        Log.d("this_is_tag", String.valueOf(response));
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
                });


            }
        });


        button_fourth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                RetrofitService service = initRetrofit();
                service.getRes().enqueue(new Callback<Resources>() {
                    @Override
                    public void onResponse(Call<Resources> call, Response<Resources> response) {
                        response.body();
                        Log.d("this_is_tag", String.valueOf(response));
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<Resources> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
                });
            }
        });


        button_five.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                RetrofitService service = initRetrofit();
                service.getOneRes().enqueue(new Callback<UnoResource>() {
                    @Override
                    public void onResponse(Call<UnoResource> call, Response<UnoResource> response) {
                        response.body();
                        Log.d("this_is_tag", String.valueOf(response));
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<UnoResource> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
                });
            }
        });


        button_six.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view) {
                RetrofitService service = initRetrofit();
                service.getOneRes404().enqueue(new Callback<UnoResource>() {
                    @Override
                    public void onResponse(Call<UnoResource> call, Response<UnoResource> response) {
                        response.body();
                        Log.d("this_is_tag", String.valueOf(response));
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<UnoResource> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
                });

            }
        });


        button_seven.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {


                RetrofitService service = initRetrofit();
                AddUserName info = new AddUserName();
                service.addUser(info).enqueue(new Callback<AddUserName>() {
                    @Override
                    public void onResponse(Call<AddUserName> call, Response<AddUserName> response) {
                        response.body();
                        Log.d("this_is_tag", String.valueOf(response));
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<AddUserName> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
                });

            }
        });


        button_eight.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                RetrofitService service = initRetrofit();
                UpdateInfo info = new UpdateInfo();
                service.updateInfo(info).enqueue(new Callback<UpdateInfo>() {
                    @Override
                    public void onResponse(Call<UpdateInfo> call, Response<UpdateInfo> response) {
                        response.body();
                        Log.d("this_is_tag", String.valueOf(response));
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<UpdateInfo> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
                });
            }
        });


        button_nine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                RetrofitService service = initRetrofit();
                UpdateInfo info = new UpdateInfo();
                service.PatchInfo(info).enqueue(new Callback<PatchInfo>() {
                    @Override
                    public void onResponse(Call<PatchInfo> call, Response<PatchInfo> response) {
                        response.body();
                        Log.d("this_is_tag", String.valueOf(response));
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<PatchInfo> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
                });
            }
        });


        button_ten.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                RetrofitService service = initRetrofit();
                service.deleteAll().enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {
                        response.body();
                        Log.d("this_is_tag", String.valueOf(response));
                        Log.d("this_is_tag", new Gson().toJson(response.body()));
                        textView_retrofit.setText("Успешно!");
                    }
                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {
                        Log.d(getClass().toString(), t.getMessage());
                        Log.d("this_is_tag", "Ошибка");
                        textView_retrofit.setText("Ошибка!");
                    }
                });
            }
        });

    }

    private void assertTrue(boolean successful) {
    }


}