package com.velmamukanga.mylibrary.client;

import com.velmamukanga.mylibrary.interfaces.MealAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static Retrofit retrofit = null;

    public static MealAPI getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(MealAPI.class);

    }
}
