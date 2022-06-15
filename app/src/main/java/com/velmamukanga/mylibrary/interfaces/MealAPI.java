package com.velmamukanga.mylibrary.interfaces;

import com.velmamukanga.mylibrary.models.RandomMeal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealAPI {
    @GET("search.php")
    Call<RandomMeal> getrandommeal(
            @Query("s") String letter
    );

}
