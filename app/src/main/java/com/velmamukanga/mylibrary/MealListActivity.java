package com.velmamukanga.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.velmamukanga.mylibrary.adapters.MealListAdapter;
import com.velmamukanga.mylibrary.client.Client;
import com.velmamukanga.mylibrary.interfaces.MealAPI;
import com.velmamukanga.mylibrary.models.Meal;
import com.velmamukanga.mylibrary.models.RandomMeal;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealListActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private MealListAdapter mAdapter;
    private static final String TAG = MealListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);
        ButterKnife.bind(this);
        
        getMeals("chicken");
    }

    private void  getMeals(String location) {
       MealAPI client = Client.getClient();
       Call<RandomMeal> randomMealCall = client.getrandommeal(location);
       randomMealCall.enqueue(new Callback<RandomMeal>() {
           @Override
           public void onResponse(Call<RandomMeal> call, Response<RandomMeal> response) {
               if (response.isSuccessful()) {

                   RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MealListActivity.this);

                   mRecyclerView.setLayoutManager(layoutManager);
                   mAdapter = new MealListAdapter(getApplicationContext(), response.body().getMeals());
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setHasFixedSize(true);

        Log.d("MealListActivity", "count" + mAdapter.getItemCount());

               }
           }

           @Override
           public void onFailure(Call<RandomMeal> call, Throwable t) {
               Log.e("MealListActivity", "error", t);

           }
       });

    }


}
