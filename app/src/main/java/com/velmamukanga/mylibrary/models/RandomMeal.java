package com.velmamukanga.mylibrary.models;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RandomMeal {

    @SerializedName("meals")
    @Expose
    private List<Meal> meals = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public RandomMeal() {
    }

    /**
     *
     * @param meals
     */
    public RandomMeal(List<Meal> meals) {
        super();
        this.meals = meals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

}

