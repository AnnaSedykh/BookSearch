package com.annasedykh.booksearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@link ResultActivity} displays search result
 */
public class ResultActivity extends AppCompatActivity {
    private static final String TAG = "ResultActivity";

    private static final int MAX_RESULT = 40;
    private static final int CODE_RESULT_NOT_FOUND = 1;

    private SearchApi api;
    private BooksAdapter adapter;

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recycler_view)
    RecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private String queryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        App app = (App) getApplication();
        api = app.getApi();

        setToolbar();
        fab.hide();

        queryString = getIntent().getStringExtra(MainActivity.QUERY);
        adapter = new BooksAdapter();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        if (recyclerView.computeVerticalScrollOffset() != 0) {
                            fab.show();
                        }
                        break;
                    default:
                        fab.hide();
                        break;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        loadData();
    }

    @OnClick(R.id.fab)
    void smoothScroll() {
        recycler.smoothScrollToPosition(0);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Query the Google Books API dataset and return a list of {@link Book} objects.
     */
    private void loadData() {
        Call<SearchResult> call = api.searchBooks(queryString, MAX_RESULT);
        //Call is executed asynchronously
        call.enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                Log.i(TAG, "search onResponse: ");
                SearchResult result = response.body();
                if (result != null && result.getTotalItems() > 0) {
                    adapter.setData(result.getBooks());
                    progressBar.setVisibility(View.GONE);
                } else {
                    Intent notFoundIntent = new Intent(ResultActivity.this, ResultNotFoundActivity.class);
                    notFoundIntent.putExtra(MainActivity.QUERY, queryString);
                    startActivityForResult(notFoundIntent, CODE_RESULT_NOT_FOUND);
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.w(TAG, "search onFailure: ", t);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CODE_RESULT_NOT_FOUND:
                if (resultCode == RESULT_OK) {
                    finish();
                }
        }
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
