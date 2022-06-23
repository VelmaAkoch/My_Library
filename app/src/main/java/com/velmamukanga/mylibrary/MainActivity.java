package com.velmamukanga.mylibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.button)
    Button mFindMealButton;
    @BindView(R.id.savedMealsButton)
    Button mSavedMealsButton;


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//
//    private DatabaseReference mSearchedLocationReference;
//
//    private ValueEventListener mSearchedLocationReferenceListener;

//    @BindView(R.id.button) Button button;
//    @BindView(R.id.savedMealsButton) Button mSavedMealsButton;
//    @BindView(R.id.button) Button mFindMealButton;
//    @BindView(R.id.locationEditText) EditText mLocationEditText;
//    @BindView(R.id.appNameTextView) TextView mAppNameTextView;
//    @BindView(R.id.mealImageView) ImageView mImageLabel;
//    @BindView(R.id.mealNameTextView) TextView mNameLabel;
//    @BindView(R.id.cuisineTextView) TextView mCategoriesLabel;
//    @BindView(R.id.ratingTextView) TextView mRatingLabel;
//    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
//    @BindView(R.id.saveMealButton) TextView mSaveRestaurantButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindMealButton.setOnClickListener(this);
        mSavedMealsButton.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                } else {

                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void onClick(View v) {
        if (v == mFindMealButton) {
            Intent intent = new Intent(MainActivity.this, MealListActivity.class);
            startActivity(intent);
        }

        if (v == mSavedMealsButton) {
            Intent intent = new Intent(MainActivity.this, SavedMealListActivity.class);
            startActivity(intent);
        }

    }
}


//        mSearchedLocationReference = FirebaseDatabase
//                .getInstance()
//                .getReference();
//                mSearchedLocationReference.child();
//
//        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener(){
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
//                    String location = locationSnapshot.getValue().toString();
//                    Log.d("Locations updated", "location: " + location);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////        ButterKnife.bind(this);
//        Button button = (Button)findViewById(R.id.button);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
//
//        mFindMealButton.setOnClickListener(this);
//        mSavedMealsButton.setOnClickListener(this);
//
//    }

//    @Override
//    public void onClick (View v){
//        if (v == mFindMealButton) {
//              String location = mLocationEditText.getText().toString();
//
//              saveLocationToFirebase(location);
//
//               if (!(location).equals("")) {
//                        addToSharedPreferences(location);
//               }
//
//
//            if (v == mSavedMealsButton) {
//                Intent intent = new Intent(MainActivity.this, SavedMealListActivity.class);
//                startActivity(intent);
//            }
//
//              Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
//              intent.putExtra("location", location);
//              startActivity(intent);
//        }
//    }
//
//    public void saveLocationToFirebase (String location){
//            mSearchedLocationReference.setValue(location);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
//    }
//}