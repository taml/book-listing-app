package com.example.android.booklistingapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
        StringBuilder category = currentBook.getCategory();
        categoryTextView.setText(category);

        TextView firstLetterTextView = (TextView) listItemView.findViewById(R.id.firstLetter);
        String firstLetter = currentBook.getFirstCategory();

        switch (firstLetter) {
            case "A":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.a));
                break;
            case "B":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.b));
                break;
            case "C":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.c));
                break;
            case "D":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.d));
                break;
            case "E":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.e));
                break;
            case "F":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.f));
                break;
            case "G":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.g));
                break;
            case "H":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.h));
                break;
            case "I":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.i));
                break;
            case "J":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.j));
                break;
            case "K":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.k));
                break;
            case "L":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.l));
                break;
            case "M":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.m));
                break;
            case "N":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.n));
                break;
            case "O":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.o));
                break;
            case "P":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.p));
                break;
            case "Q":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.q));
                break;
            case "R":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.r));
                break;
            case "S":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.s));
                break;
            case "T":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.t));
                break;
            case "U":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.u));
                break;
            case "W":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.w));
                break;
            case "X":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.x));
                break;
            case "Y":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.y));
                break;
            case "Z":
                firstLetterTextView.setText(firstLetter);
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.z));
                break;
            default:
                firstLetterTextView.setText("N");
                firstLetterTextView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.defaultColor));
                break;
        }

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.author_text);
        StringBuilder author = currentBook.getAuthor();
        authorTextView.setText(author);

        return listItemView;
    }
}
