package com.velmamukanga.mylibrary.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.velmamukanga.mylibrary.R;
import com.velmamukanga.mylibrary.adapters.MealPagerAdapter;
import com.velmamukanga.mylibrary.models.Meal;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private MealPagerAdapter adapterViewPager;
    List<Meal> mMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_detail);
        ButterKnife.bind(this);

        mMeals = Parcels.unwrap(getIntent().getParcelableExtra("meals"));
        int startingPosition = getIntent().getIntExtra("position", 0);


        adapterViewPager = new MealPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mMeals);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

    }
}