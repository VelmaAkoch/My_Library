package com.velmamukanga.mylibrary.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.velmamukanga.mylibrary.R;
import com.velmamukanga.mylibrary.models.Meal;
import com.velmamukanga.mylibrary.ui.MealDetailActivity;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealViewHolder> {

    private List<Meal> meals;
    private Context mContext;

    public MealListAdapter(Context context, List<Meal> meals) {
        mContext = context;
        this.meals = meals;
    }


    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_list_item, parent, false);
        MealViewHolder viewHolder = new MealViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        holder.bindMeal(meals.get(position));

    }

    @Override
    public int getItemCount() {
        return meals.size();
    }


    public class MealViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.mealImageView) ImageView mMealImageView;
        @BindView(R.id.mealNameTextViewName) TextView mealNameTextViewName;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;
        private Context mContext;


            public MealViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                mContext = itemView.getContext();
                itemView.setOnClickListener(this);
            }

            public void bindMeal(Meal meal) {
                Picasso.get().load(meal.getStrMealThumb()).into(mMealImageView);
                mealNameTextViewName.setText(meal.getStrMeal());
                mCategoryTextView.setText(meal.getStrArea());
                mRatingTextView.setText(meal.getStrCategory());
            }

            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, MealDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("meals", Parcels.wrap(meals));
                mContext.startActivity(intent);
            }

    }
}












