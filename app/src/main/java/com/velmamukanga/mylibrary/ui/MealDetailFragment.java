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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.velmamukanga.mylibrary.R;
import com.velmamukanga.mylibrary.constants.Constants;
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
public class MealDetailFragment extends Fragment implements View.OnClickListener{
        @BindView(R.id.mealImageView) ImageView mImageLabel;
        @BindView(R.id.mealNameTextView) TextView mNameLabel;
        @BindView(R.id.cuisineTextView) TextView mCategoriesLabel;
        @BindView(R.id.ratingTextView) TextView mRatingLabel;
        @BindView(R.id.phoneTextView) TextView mPhoneLabel;
        @BindView(R.id.saveMealButton) TextView mSaveMealButton;


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

            mSaveMealButton.setOnClickListener(this);

            return view;
        }


        @Override
        public void onClick(View v) {
            if (v == mSaveMealButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference mealRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_MEALS)
                    .child(uid);

            DatabaseReference pushRef = mealRef.push();
            String pushId = pushRef.getKey();
            pushRef.get();
            pushRef.setValue(meal);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();

            }
        }

}
