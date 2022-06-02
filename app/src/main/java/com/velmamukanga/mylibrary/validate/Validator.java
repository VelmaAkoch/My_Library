package com.velmamukanga.mylibrary.validate;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.util.jar.Attributes;

public class Validator {
    public boolean validateName(TextInputLayout name){
        if (name.getEditText().getText().toString().isEmpty()){
            name.setError("please enter your name");
            return false;
        }
        return true;


    }

    public boolean validateEmail(EditText name){
        if (name.getText().toString().isEmpty()){
            name.setError("please enter your email");
            return false;
        }
        return true;


    }

    public boolean validatePassword(EditText name){
        if (name.getText().toString().isEmpty()){
            name.setError("please enter your password");
            return false;
        }
        else if (name.getText().toString().length()<8){
            name.setError("password too small");
            return false;
        }
        return true;


    }
}
