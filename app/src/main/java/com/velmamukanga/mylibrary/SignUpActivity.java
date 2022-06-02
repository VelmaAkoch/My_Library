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

public class SignUpActivity extends AppCompatActivity {


    @BindView(R.id.button2)
    Button button2;

    @BindView(R.id.velma)
    TextInputLayout name;

    @BindView(R.id.editTextTextEmailAddress)
   EditText email;

    @BindView(R.id.editTextTextPassword)
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Validator susan = new Validator();

        ButterKnife.bind(this);
        Button button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                if (!susan.validateName(name) || ! susan.validateEmail(email) || ! susan.validatePassword(password)){
                    return;
                }
                String username = name.getEditText().getText().toString();
                Toast.makeText(SignUpActivity.this,username,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}