package com.annasedykh.booksearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
    private int totalItems;
    @SerializedName("items")
    private List<Book> books;

    public int getTotalItems() {
        return totalItems;
    }

    public List<Book> getBooks() {
        return books;
    }
}
