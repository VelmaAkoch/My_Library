package com.velmamukanga.mylibrary.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.velmamukanga.mylibrary.constants.Constants;
import com.velmamukanga.mylibrary.models.Meal;
import com.velmamukanga.mylibrary.ui.MealDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseMealViewHolder extends RecyclerViewAdapter implements View.OnClickListener{

    View mView;
    Context mContext;

    public FirebaseMealViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void meal(Meal meal) {
        ImageView restaurantImageView = (ImageView) mView.findViewById(R.id.restaurantImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.mealNameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);

         Picasso.get().load(meal.getStrMealThumb()).into(mMealImageView);

        nameTextView.setText(meal.getName());
        categoryTextView.setText(meal.getCategories().get(0).getTitle());
        ratingTextView.setText("Rating: " + meal.getRating() + "/5");
    }

    @Override
    public void onClick(View v) {
        final ArrayList<Meal> meal = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MEALS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    meals.add(snapshot.getValue(Meal.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MealDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("restaurants", Parcels.wrap(meals));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
