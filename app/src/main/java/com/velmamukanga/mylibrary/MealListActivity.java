//package com.velmamukanga.mylibrary;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.SearchView;
//import android.widget.TextView;
//
//import com.velmamukanga.mylibrary.adapters.MealListAdapter;
//import com.velmamukanga.mylibrary.client.Client;
//import com.velmamukanga.mylibrary.constants.Constants;
//import com.velmamukanga.mylibrary.interfaces.MealAPI;
//import com.velmamukanga.mylibrary.models.Meal;
//import com.velmamukanga.mylibrary.models.RandomMeal;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MealListActivity extends AppCompatActivity {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mRecentAddress;
//
//
//    private static final String TAG = MealListActivity.class.getSimpleName();
//    private MealListAdapter mAdapter;
//
//
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
//    @BindView(R.id.errorTextView) TextView mErrorTextView;
//    @BindView(R.id.progressBar) ProgressBar mProgressBar;
//    public List<Meal> meals;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_meals);
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//        if (mRecentAddress != null) {
//            fetchMeals(mRecentAddress);
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search, menu);
//        ButterKnife.bind(this);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//
//        MenuItem menuItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String location) {
//                addToSharedPreferences(location);
//                fetchMeals(location);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String location) {
//                return false;
//            }
//        });
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item){
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void showFailureMessage() {
//        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }
//
//    private void showUnsuccessfulMessage() {
//        mErrorTextView.setText("Something went wrong. Please try again later");
//        mErrorTextView.setVisibility(View.VISIBLE);
//    }
//
//    private void showRestaurants() {
//        mRecyclerView.setVisibility(View.VISIBLE);
//    }
//
//    private void hideProgressBar() {
//        mProgressBar.setVisibility(View.GONE);
//    }
//
//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }
//
//    private void fetchMeals(String location){
//        YelpApi client = YelpClient.getClient();
//        Call<YelpBusinessesSearchResponse> call = client.getMeals(location, "restaurants");
//        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
//            @Override
//            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
//
//                hideProgressBar();
//
//                if (response.isSuccessful()) {
//                    meals = response.body().getBusinesses();
//                    mAdapter = new MealListAdapter(MealListActivity.this, meals);
//                    mRecyclerView.setAdapter(mAdapter);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MealListActivity.this);
//                    mRecyclerView.setLayoutManager(layoutManager);
//                    mRecyclerView.setHasFixedSize(true);
//
//                    showRestaurants();
//                } else {
//                    showUnsuccessfulMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
//                Log.e(TAG, "onFailure: ",t );
//                hideProgressBar();
//                showFailureMessage();
//            }
//
//        });
//    }
//}
//
//
////            @Override
////            public boolean onQueryTextChange(String location) {
////                return false;
////            }
////        });
////
////        return true;
////    }
////            YelpApi client = YelpClient.getClient();
////            Call<YelpBusinessesSearchResponse> call = client.getMeals(mRecentAddress, "restaurants");
////            call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
////                @Override
////                public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
////
////                    hideProgressBar();
////
////                    if (response.isSuccessful()) {
////                        restaurants = response.body().getBusinesses();
////                        mAdapter = new RestaurantListAdapter(MealListActivity.this, restaurants);
////                        mRecyclerView.setAdapter(mAdapter);
////                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RestaurantListActivity.this);
////                        mRecyclerView.setLayoutManager(layoutManager);
////                        mRecyclerView.setHasFixedSize(true);
////
////                        showRestaurants();
////                    } else {
////                        showUnsuccessfulMessage();
////                    }
////                }
////
////                @Override
////                public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
////                    Log.e(TAG, "onFailure: ", t);
////                    hideProgressBar();
////                    showFailureMessage();
////                }
////
////            });
////        }
////    }
////
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.menu_search, menu);
////        ButterKnife.bind(this);
////
////        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
////        mEditor = mSharedPreferences.edit();
////
////        MenuItem menuItem = menu.findItem(R.id.action_search);
////        SearchView searchView = (SearchView) menuItem.getActionView();
////
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String location) {
////                addToSharedPreferences(location);
//////                YelpApi client = YelpClient.getClient();
////                Call<YelpBusinessesSearchResponse> call = client.getRestaurants(location, "restaurants");
////                call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
////                    @Override
////                    public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
////
////                        hideProgressBar();
////
////                        if (response.isSuccessful()) {
////                            meals = response.body().getBusinesses();
////                            mAdapter = new MealListAdapter(MealListActivity.this, meals);
////                            mRecyclerView.setAdapter(mAdapter);
////                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MealListActivity.this);
////                            mRecyclerView.setLayoutManager(layoutManager);
////                            mRecyclerView.setHasFixedSize(true);
////
////                            showRestaurants();
////                        } else {
////                            showUnsuccessfulMessage();
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
////                        Log.e(TAG, "onFailure: ", t);
////                        hideProgressBar();
////                        showFailureMessage();
////                    }
////
////                });
////                return false;
////            }
////
//
//
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_meal_list);
////        ButterKnife.bind(this);
////
////        Intent intent = getIntent();
////
////        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
////        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//////
//////        private void addToSharedPreferences(String location) {
//////            mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//////        }
//////
//////        getMeals("chicken");
//////
//////        String location = intent.getStringExtra("location");
//////
//////        @Override
//////        public boolean onCreateOptionsMenu(Menu menu) {
//////            MenuInflater inflater = getMenuInflater();
//////            inflater.inflate(R.menu.menu_search, menu);
//////            ButterKnife.bind(this);
//////
//////            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//////            mEditor = mSharedPreferences.edit();
//////
//////            MenuItem menuItem = menu.findItem(R.id.action_search);
//////            SearchView searchView = (SearchView) menuItem.getActionView();
//////
//////            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////
////        if(mRecentAddress != null){
////            YelpApi client = YelpClient.getClient();
////            Call<YelpBusinessesSearchResponse> call = client.getMeals(mRecentAddress, "meals");
////            call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
//////                @Override
//////                public boolean onQueryTextSubmit(String location) {
//////                    addToSharedPreferences(location);
////                        @Override
////                        public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
////
////                            hideProgressBar();
////
////                            if (response.isSuccessful()) {
////                                meals = response.body().getBusinesses();
////                                mAdapter = new MealListAdapter(MealListActivity.this, meals);
////                                mRecyclerView.setAdapter(mAdapter);
////                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MealListActivity.this);
////                                mRecyclerView.setLayoutManager(layoutManager);
////                                mRecyclerView.setHasFixedSize(true);
////
////                                showMeals();
////                            } else {
////                                showUnsuccessfulMessage();
////                            }
////                        }
////
////                        @Override
////                        public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
////                            Log.e(TAG, "onFailure: ",t );
////                            hideProgressBar();
////                            showFailureMessage();
////                        }
////
////                    });
////                    return false;
////                }
////
////                @Override
////                public boolean onQueryTextChange(String location) {
////                    return false;
////                }
////            });
////
////            return true;
////        }
////
////        @Override
////        public boolean onOptionsItemSelected(MenuItem item) {
////            return super.onOptionsItemSelected(item);
////        }
////
////
////    }
////
//////    private void  getMeals(String location) {
//////       MealAPI client = Client.getClient();
//////       Call<RandomMeal> randomMealCall = client.getrandommeal(location);
//////       randomMealCall.enqueue(new Callback<RandomMeal>() {
//////           @Override
//////           public void onResponse(Call<RandomMeal> call, Response<RandomMeal> response) {
//////               if (response.isSuccessful()) {
//////
//////                   RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MealListActivity.this);
//////
//////                   mRecyclerView.setLayoutManager(layoutManager);
//////                   mAdapter = new MealListAdapter(getApplicationContext(), response.body().getMeals());
//////        mRecyclerView.setAdapter(mAdapter);
//////
//////        mRecyclerView.setHasFixedSize(true);
//////
//////        Log.d("MealListActivity", "count" + mAdapter.getItemCount());
//////
//////               }
//////           }
//////
//////           @Override
//////           public void onFailure(Call<RandomMeal> call, Throwable t) {
//////               Log.e("MealListActivity", "error", t);
//////
//////           }
//////       });
//////
//////    }
//////
//
//
//
