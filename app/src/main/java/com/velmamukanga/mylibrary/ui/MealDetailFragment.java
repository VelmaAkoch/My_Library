package com.velmamukanga.mylibrary.ui;

import android.content.Intent;
import android.icu.util.ULocale;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.velmamukanga.mylibrary.R;
import com.velmamukanga.mylibrary.models.Meal;
import com.velmamukanga.mylibrary.models.RandomMeal;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MealDetailFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MealDetailFragment extends Fragment {
        @BindView(R.id.mealImageView) ImageView mImageLabel;
        @BindView(R.id.mealNameTextView) TextView mNameLabel;
        @BindView(R.id.cuisineTextView) TextView mCategoriesLabel;
        @BindView(R.id.ratingTextView) TextView mRatingLabel;
        @BindView(R.id.phoneTextView) TextView mPhoneLabel;
        @BindView(R.id.saveMealButton) TextView mSaveRestaurantButton;


        private Meal meal;

        public MealDetailFragment() {
            // Required empty public constructor
        }


        public static MealDetailFragment newInstance(Meal meal) {
            MealDetailFragment mealDetailFragment = new MealDetailFragment();
            Bundle args = new Bundle();
            args.putParcelable("meal", Parcels.wrap(meal));
            mealDetailFragment.setArguments(args);
            return mealDetailFragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            assert getArguments() != null;
            meal = Parcels.unwrap(getArguments().getParcelable("meal"));
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            // Inflate the layout for this fragment

            View view = inflater.inflate(R.layout.fragment_meal_detail, container, false);
            ButterKnife.bind(this, view);
            Picasso.get().load(meal.getStrMealThumb()).into(mImageLabel);

//            List<String> categories = new ArrayList<>();


            mNameLabel.setText(meal.getStrMeal());
            mCategoriesLabel.setText(meal.getStrArea());
            mRatingLabel.setText(meal.getStrCategory());
            mPhoneLabel.setText(meal.getStrInstructions());
            return view;


        }


}
