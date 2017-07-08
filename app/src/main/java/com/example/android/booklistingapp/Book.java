package com.example.android.booklistingapp;

public class Book {

    private String mTitle;
    private String mAuthor;
    private String mCategory;
    private String mDescription;
    private String mUrl;

    public Book(String title, String author, String category, String description, String url){
        mTitle = title;
        mAuthor = author;
        mCategory = category;
        mDescription = description;
        mUrl = url;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public String getCategory(){
        return mCategory;
    }

    public String getmDescription(){
        return mDescription;
    }

    public String getUrl(){
        return mUrl;
    }
}
