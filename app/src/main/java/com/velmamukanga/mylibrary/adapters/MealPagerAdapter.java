package com.velmamukanga.mylibrary.adapters;

import android.support.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.velmamukanga.mylibrary.models.Meal;
import com.velmamukanga.mylibrary.models.RandomMeal;
import com.velmamukanga.mylibrary.ui.MealDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class MealPagerAdapter extends FragmentPagerAdapter {
    private List<Meal> meals;

    public MealPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Meal> meals) {
        super(fm, behavior);
        this.meals = meals;
    }

    @Override
    public Fragment getItem(int position) {
        return MealDetailFragment.newInstance(meals.get(position));
    }

    @Override
    public int getCount() {
        return meals.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return meals.get(position).getStrMeal();
    }

}
