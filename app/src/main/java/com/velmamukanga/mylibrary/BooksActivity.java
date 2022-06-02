package com.velmamukanga.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.velmamukanga.mylibrary.adapters.BooksArrayAdapter;

public class BooksActivity extends AppCompatActivity {
    ListView velma;
    BooksArrayAdapter akoch;

    private String[] restaurant = new String[] {"Hunting of the House", "Seven days in june", "Treasure Island", "Snow Crash", "Stone of Farewell"};
    private String[] cuisines = new String[] {"Horror Fiction", "Love Story Novel", "Action Novels", "Reality Novels", "Sorrow Novel"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        velma = findViewById(R.id.ListView);
        akoch = new BooksArrayAdapter(BooksActivity.this, android.R.layout.simple_list_item_1, restaurant, cuisines);
        velma.setAdapter(akoch);

    }
}

