//package com.velmamukanga.mylibrary.ui;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.velmamukanga.mylibrary.R;
//import com.velmamukanga.mylibrary.adapters.FirebaseMealViewHolder;
//import com.velmamukanga.mylibrary.constants.Constants;
//import com.velmamukanga.mylibrary.models.Meal;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class SavedMealListActivity extends AppCompatActivity {
//
//    private DatabaseReference mRestaurantReference;
//    private FirebaseRecyclerAdapter<Meal, FirebaseMealViewHolder> mFirebaseAdapter;
//
//    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
//    @BindView(R.id.errorTextView) TextView mErrorTextView;
//    @BindView(R.id.progressBar) ProgressBar mProgressBar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_meals);
//        ButterKnife.bind(this);
//
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
//
//        mMealReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_MEAlS)
//                .child(uid);
//
//        setUpFirebaseAdapter();
//        hideProgressBar();
//        showMeals();
//    }
//
//    private void setUpFirebaseAdapter() {
//        FirebaseRecyclerOptions<Meal> options =
//                new FirebaseRecyclerOptions.Builder<Meal>()
//                        .setQuery(mMealReference, Meal.class)
//                        .build();
//
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Meal, FirebaseMealViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@androidx.annotation.NonNull FirebaseMealViewHolder holder, int position, @androidx.annotation.NonNull Meal model) {
//
//            }
//
//            @NonNull
//            @Override
//            public FirebaseMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meal_list_item, parent, false);
//                return new FirebaseMealViewHolder(view);
//            }
//        };
//
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mFirebaseAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(mFirebaseAdapter!= null) {
//            mFirebaseAdapter.stopListening();
//        }
//    }
//
//    private void showMeals() {
//        mRecyclerView.setVisibility(View.VISIBLE);
//    }
//
//    private void hideProgressBar() {
//        mProgressBar.setVisibility(View.GONE);
//    }
//}
