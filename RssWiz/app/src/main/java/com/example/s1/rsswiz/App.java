package com.example.s1.rsswiz;

/**
 * Created by S1 on 1/18/2017.
 */
import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.s1.rsswiz.AngryApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App extends Application {

    private static AngryApi api;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

     /*   Gson gson = new GsonBuilder()
                .setLenient()
                .create();*/

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        api = retrofit.create(AngryApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static AngryApi getApi() {
        return api;
    }
}
