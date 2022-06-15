package com.velmamukanga.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.velmamukanga.mylibrary.validate.Validator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.button3) Button button3;
    @BindView(R.id.editTextTextEmailAddress) EditText email;
    @BindView(R.id.editTextTextPassword) EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        Button button = (Button)findViewById(R.id.button3);
        Validator validator = new Validator();
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if (!validator.validateEmail(email) || ! validator.validatePassword(password)){
                    return;
                }
                Toast.makeText(LoginActivity.this,"successful",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, MealListActivity.class);
                startActivity(intent);

            }
        });
    }
}