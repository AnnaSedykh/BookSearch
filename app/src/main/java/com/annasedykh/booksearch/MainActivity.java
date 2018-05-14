package com.annasedykh.booksearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int MAX_RESULT = 40;

    private SearchApi api;

    private EditText queryText;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        App app = (App) getApplication();
        api = app.getApi();

        queryText = findViewById(R.id.query_text);
        searchBtn = findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = queryText.getText().toString();
                Call<SearchResult> call = api.searchBooks(query, MAX_RESULT);
                call.enqueue(new Callback<SearchResult>() {
                    @Override
                    public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                        SearchResult books = response.body();
                        Log.i(TAG, "search onResponse: " + books);

                    }

                    @Override
                    public void onFailure(Call<SearchResult> call, Throwable t) {
                        Log.w(TAG, "search onFailure: ", t);
                    }
                });


            }
        });
    }
}
