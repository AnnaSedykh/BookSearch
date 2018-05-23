package com.annasedykh.booksearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * {@link MainActivity} - search activity
 */
public class MainActivity extends AppCompatActivity {
    public static final String QUERY = "query";

    @BindView(R.id.query_text)
    EditText queryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.search_btn)
    void search() {
        String queryString = queryText.getText().toString();
        Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
        resultIntent.putExtra(QUERY, queryString);
        startActivity(resultIntent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        queryText.setText("");
    }
}
