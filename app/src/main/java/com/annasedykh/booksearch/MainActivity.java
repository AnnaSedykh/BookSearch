package com.annasedykh.booksearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * {@link MainActivity} - search activity
 */
public class MainActivity extends AppCompatActivity {
    public static final String QUERY = "query";

    private EditText queryText;
    private Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryText = findViewById(R.id.query_text);
        searchBtn = findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryString = queryText.getText().toString();
                Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class);
                resultIntent.putExtra("query", queryString);
                startActivity(resultIntent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        queryText.setText("");
    }
}
