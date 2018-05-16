package com.annasedykh.booksearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * {@link SearchApi} interface for working with Google Books API using Retrofit2
 */
public interface SearchApi {

    /**
     * GET request
     * @return a specified subset of resources within a collection
     */
    @GET("volumes")
    Call<SearchResult> searchBooks(@Query("q") String query, @Query("maxResults") int maxResults);

}
