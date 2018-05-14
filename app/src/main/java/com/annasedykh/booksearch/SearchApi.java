package com.annasedykh.booksearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchApi {

    @GET("volumes")
    Call<SearchResult> searchBooks(@Query("q") String query, @Query("maxResults") int maxResults);

}
