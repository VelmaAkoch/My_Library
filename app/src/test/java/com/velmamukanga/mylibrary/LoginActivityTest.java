package com.velmamukanga.mylibrary;

import static org.junit.Assert.*;

import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {
    LoginActivity loginActivity;

    @Before
    public void setUp() throws Exception {
        loginActivity = Robolectric.buildActivity(LoginActivity.class).create().start().resume().get();
    }

    @Test
    public void checkButtonText(){
        Button button = loginActivity.findViewById(R.id.button3);
        assertEquals("login", button.getText());
    }
}