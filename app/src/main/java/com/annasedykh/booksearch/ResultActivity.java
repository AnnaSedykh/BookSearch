package com.annasedykh.booksearch;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    private TextView notFoundView;
    private TextView recommend;
    private String queryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        App app = (App) getApplication();
        api = app.getApi();

        setToolbar();

        queryString = getIntent().getStringExtra(MainActivity.QUERY);

        adapter = new BooksAdapter();
        recycler = findViewById(R.id.recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        fab.show();
                        break;
                    default:
                        fab.hide();
                        break;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recycler.smoothScrollToPosition(0);
            }
        });

        loadData();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadData() {
        Call<SearchResult> call = api.searchBooks(queryString, MAX_RESULT);
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.i(TAG, "search onResponse: ");
                SearchResult result = response.body();
                if (result != null && result.getTotalItems() > 0) {
                    adapter.setData(result.getBooks());
                    findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                } else {
                    showResultNotFound();
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.w(TAG, "search onFailure: ", t);
            }
        });
    }

    private void showResultNotFound() {
        setContentView(R.layout.result_not_found);
        notFoundView = findViewById(R.id.result_not_found);
        notFoundView.setText(getString(R.string.result_not_found, queryString));
        recommend = findViewById(R.id.recommend);
        recommend.setText(getString(R.string.recommend));
        setToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
