package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>>{

    public static final String LOG_TAG = ListingActivity.class.getName();
    // URL for book data from the Google books api
    private static final String GOOGLE_BOOKS_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    // Adapter for the list of books
    private BookAdapter mAdapter;
    // Constant value for the book loader ID.
    private static final int BOOK_LOADER_ID = 11;
    // Query result
    private String query = "art";
    // Full query URL
    private String fullQueryURL = GOOGLE_BOOKS_URL + query;

    private TextView mEmptyTextView;
    private ProgressBar mProgressView;

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

        mProgressView = (ProgressBar) findViewById(R.id.progress);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website where a user can get the book.
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current book that was clicked on
                Book currentBook = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri bookUri = Uri.parse(currentBook.getUrl());

                // Create a new intent to view the book URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
            Log.i(LOG_TAG, "Loader on init");
        } else {
            mProgressView.setVisibility(View.GONE);
            mEmptyTextView.setText(R.string.no_internet);
        }

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

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        // Create a new loader for the given URL
        Log.i(LOG_TAG, "Loader on create");
        return new BookLoader(this, fullQueryURL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        // Set empty state text to display "No books found."
        mProgressView.setVisibility(View.GONE);
        mEmptyTextView.setText(R.string.no_books);

        // Clear the adapter of previous book data
        mAdapter.clear();
        Log.i(LOG_TAG, "Loader on finished");
        // If there is a valid list of {@link Books}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.
        Log.i(LOG_TAG, "Loader on reset");
        mAdapter.clear();
    }
}
