package com.annasedykh.booksearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Book {

    private String id;
    @SerializedName("volumeInfo")
    private BookInfo info;


    class BookInfo{

        private String title;
        private List<String> authors;
        private String description;
        private String infoLink;
        private String webReaderLink;
        private Map<String, String> imageLinks;

    }

}
