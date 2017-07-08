package com.example.android.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> appWords) {
        super(context,0, appWords);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book currentBook = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title_text);
        String title = currentBook.getTitle();
        titleTextView.setText(title);

        TextView descriptionTextView = (TextView) listItemView.findViewById(R.id.description_text);
        String description = currentBook.getmDescription();
        descriptionTextView.setText(description);

        TextView categoryTextView = (TextView) listItemView.findViewById(R.id.category_text);
        String category = currentBook.getCategory();
        categoryTextView.setText(category);

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author_text);
        String author = currentBook.getAuthor();
        authorTextView.setText(author);


        return listItemView;
    }
}
