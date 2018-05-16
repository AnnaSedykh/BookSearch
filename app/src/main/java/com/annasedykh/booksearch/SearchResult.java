package com.annasedykh.booksearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * {@link SearchResult} model class for search result.
 */
public class SearchResult {
    /** Total books quantity */
    private int totalItems;

    /** List of  {@link Book} objects*/
    @SerializedName("items")
    private List<Book> books;

    public int getTotalItems() {
        return totalItems;
    }

    public List<Book> getBooks() {
        return books;
    }
}
