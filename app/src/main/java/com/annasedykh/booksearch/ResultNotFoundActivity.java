package com.annasedykh.booksearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link ResultNotFoundActivity} starts if not found any books on request
 */

public class ResultNotFoundActivity extends AppCompatActivity {

    @BindView(R.id.result_not_found)
    TextView notFoundView;
    @BindView(R.id.recommend)
    TextView recommend;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String queryString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_not_found);

        ButterKnife.bind(this);

        setToolbar();
        queryString = getIntent().getStringExtra(MainActivity.QUERY);
        notFoundView.setText(getString(R.string.result_not_found, queryString));
        recommend.setText(getString(R.string.recommend));
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_OK);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
