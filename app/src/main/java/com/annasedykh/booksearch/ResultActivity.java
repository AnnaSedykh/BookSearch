package com.annasedykh.booksearch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    private static final String TAG = "ResultActivity";

    private static final int MAX_RESULT = 40;

    private SearchApi api;

    private FloatingActionButton fab;
    private RecyclerView recycler;
    private BooksAdapter adapter;
    private String queryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        App app = (App) getApplication();
        api = app.getApi();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        queryString = getIntent().getStringExtra(MainActivity.QUERY);

        adapter = new BooksAdapter();
        recycler = findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        loadData();
    }

    private void loadData(){
        Call<SearchResult> call = api.searchBooks(queryString, MAX_RESULT);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.i(TAG, "search onResponse: ");
                SearchResult result = response.body();
                adapter.setData(result.getBooks());
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.w(TAG, "search onFailure: ", t);
            }
        });

    }

}
