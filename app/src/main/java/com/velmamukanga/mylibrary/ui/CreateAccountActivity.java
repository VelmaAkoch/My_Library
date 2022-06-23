//package com.velmamukanga.mylibrary.ui;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.UserProfileChangeRequest;
//import com.velmamukanga.mylibrary.MainActivity;
//
//import java.util.Objects;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
//    public static final String TAG = CreateAccountActivity.class.getSimpleName();
//
//    @BindView(R.id.createUserButton) Button mCreateUserButton;
//    @BindView(R.id.nameEditText) EditText mNameEditText;
//    @BindView(R.id.emailEditText) EditText mEmailEditText;
//    @BindView(R.id.passwordEditText) EditText mPasswordEditText;
//    @BindView(R.id.confirmPasswordEditText) EditText mConfirmPasswordEditText;
//    @BindView(R.id.loginTextView) TextView mLoginTextView;
//    @BindView(R.id.firebaseProgressBar) ProgressBar mSignInProgressBar;
//    @BindView(R.id.loadingTextView) TextView mLoadingSignUp;
//
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
//    private String mName;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_creat_account);
//        ButterKnife.bind(this);
//
//        mAuth = FirebaseAuth.getInstance();
//
//        mLoginTextView.setOnClickListener(this);
//        mCreateUserButton.setOnClickListener(this);
//        createAuthStateListener();
//
//    }
//
//    private void showProgressBar() {
//        mSignInProgressBar.setVisibility(View.VISIBLE);
//        mLoadingSignUp.setVisibility(View.VISIBLE);
//        mLoadingSignUp.setText("Sign Up process in Progress");
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    private void hideProgressBar() {
//        mSignInProgressBar.setVisibility(View.GONE);
//        mLoadingSignUp.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (view == mLoginTextView) {
//            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            finish();
//        }
//
//        if (view == mCreateUserButton) {
//            createNewUser();
//        }
//
//    }
//
//    private void createFirebaseUserProfile(final FirebaseUser user) {
//
//        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
//                .setDisplayName(mName)
//                .build();
//
//        user.updateProfile(addProfileName).addOnCompleteListener(new OnCompleteListener<Void>() {
//
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Log.d(TAG, Objects.requireNonNull(user.getDisplayName()));
//                    Toast.makeText(CreateAccountActivity.this, "The display name has ben set", Toast.LENGTH_LONG).show();
//                }
//            }
//
//
//        });
//    }
//
//    private void createNewUser() {
//        mName = mNameEditText.getText().toString().trim();
//        final String name = mNameEditText.getText().toString().trim();
//        final String email = mEmailEditText.getText().toString().trim();
//        String password = mPasswordEditText.getText().toString().trim();
//        String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();
//
//        boolean validEmail = isValidEmail(email);
//        boolean validName = isValidName(name);
//        boolean validPassword = isValidPassword(password, confirmPassword);
//        boolean validmName = isValidName(mName);
//
//        if (!validEmail || !validName || !validPassword) return;
//
//        showProgressBar();
//
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCanceledListener(this, new OnCompleteListener<AuthResult>() {
//
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        hideProgressBar();
//
//                        if (task.isSuccessful()) {
//                            Log.d(TAG, "Authentication successful");
//                            createFirebaseUserProfile(Objects.requireNonNull(task.getResult().getUser()));
//                        } else {
//                            Toast.makeText(CreateAccountActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//        });
//
//    }
//
//    private void createAuthStateListener() {
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                final FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//
//        };
//    }
//
//    private boolean isValidEmail(String email) {
//        boolean isGoodEmail =
//                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
//        if (!isGoodEmail) {
//            mEmailEditText.setError("Please enter a valid email address");
//            return false;
//        }
//        return isGoodEmail;
//    }
//
//    private boolean isValidName(String name) {
//        if (name.equals("")) {
//            mNameEditText.setError("Please enter your name");
//            return false;
//        }
//        return true;
//    }
//
//    private boolean isValidPassword(String password, String confirmPassword) {
//        if (password.length() < 6) {
//            mPasswordEditText.setError("Please create a password containing at least 6 characters");
//            return false;
//        } else if (!password.equals(confirmPassword)) {
//            mPasswordEditText.setError("Passwords do not match");
//            return false;
//        }
//        return true;
//
//    }
//
//}
