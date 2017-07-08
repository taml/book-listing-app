package com.example.android.booklistingapp;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;
import static com.example.android.booklistingapp.ListingActivity.LOG_TAG;

public class BookLoader extends AsyncTaskLoader<List<Book>> {

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link BookLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public BookLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "Loader on start (Loader)");
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Book> loadInBackground() {
        Log.i(LOG_TAG, "Loader on background (Loader");
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of books.
        List<Book> books = QueryUtils.fetchBookData(mUrl);
        return books;
    }
}


