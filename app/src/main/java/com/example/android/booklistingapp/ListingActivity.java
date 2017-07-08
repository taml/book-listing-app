package com.example.android.booklistingapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListingActivity extends AppCompatActivity {

    private static final String LOG_TAG = "ListingActivity";
    // URL for book data from the Google books api
    private static final String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    // Adapter for the list of books
    private BookAdapter mAdapter;
    // Constant value for the book loader ID.
    private static final int BOOK_LOADER_ID = 11;
    // Query result
    private String query;
    // Full query URL
    private String fullQueryURL = GOOGLE_BOOKS_URL + query;

    private TextView mEmptyTextView;
    private ProgressBar mProgressBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        handleIntent(getIntent());

        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        // Set a custom message when there are no list items
        mEmptyTextView = (TextView) findViewById(R.id.empty_view_text);
        bookListView.setEmptyView(mEmptyTextView);

        mProgressBarView = (ProgressBar) findViewById(R.id.progress);

    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY).trim();
            if (query.contains(" ")){
                Toast.makeText(ListingActivity.this, "Search query should consist of one single term, e.g craft or science. Please try another search.", Toast.LENGTH_LONG).show();
            }
            Log.v(LOG_TAG, "Search Q: " + query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));


        return true;
    }
}
