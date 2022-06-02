package com.velmamukanga.mylibrary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;

import java.sql.Array;

public class BooksArrayAdapter extends ArrayAdapter {
    String[] books;
    String[] booksDescription;
    Context context;


    public BooksArrayAdapter(@NonNull Context context, int resource, String[] books, String[] booksDescription) {
        super(context, resource);
        this.books = books;
        this.booksDescription = booksDescription;
    }

    @Override
    public Object getItem(int position) {
        String book = books [position];
        String bookDescription = booksDescription[position];
        return String.format("%s \nServes great: %s", book , bookDescription);
    }

    @Override
    public int getCount() {
        return books .length;
    }
}
