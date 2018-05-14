package com.annasedykh.booksearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
    private int totalItems;
    @SerializedName("items")
    private List<Book> books;
}
