package com.velmamukanga.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.velmamukanga.mylibrary.adapters.BooksArrayAdapter;

public class BooksActivity extends AppCompatActivity {
    ListView velma;
    BooksArrayAdapter akoch;

    private String[] restaurant = new String[] {"Sweet Hereafter", "Cricket", "Hawthorne Fish House", "Viking Soul Food", "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell", "Me Kha Noodle Bar", "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche", "Kay's Bar", "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole"};
    private String[] cuisines = new String[] {"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        velma = findViewById(R.id.ListView);
        akoch = new BooksArrayAdapter(BooksActivity.this, android.R.layout.simple_list_item_1, restaurant, cuisines);
        velma.setAdapter(akoch);

    }
}

