package com.annasedykh.booksearch;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

/**
 * {@link Book} is a book model class.
 * Contains information related to a single book.
 */
public class Book {

    /** Book id */
    private String id;
    /** Book info */
    @SerializedName("volumeInfo")
    private BookInfo info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookInfo getInfo() {
        return info;
    }

    public void setInfo(BookInfo info) {
        this.info = info;
    }

    /**
     * {@link BookInfo} contains book information.
     */
    class BookInfo{

        /** Book title */
        private String title;
        /** Book authors */
        private List<String> authors;
        /** Book description */
        private String description;
        /** URL to view information */
        private String infoLink;
        /** A list of book's image links */
        private Map<String, String> imageLinks;


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getAuthors() {
            return authors;
        }

        public void setAuthors(List<String> authors) {
            this.authors = authors;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getInfoLink() {
            return infoLink;
        }

        public void setInfoLink(String infoLink) {
            this.infoLink = infoLink;
        }

        public Map<String, String> getImageLinks() {
            return imageLinks;
        }

        public void setImageLinks(Map<String, String> imageLinks) {
            this.imageLinks = imageLinks;
        }
    }

}
