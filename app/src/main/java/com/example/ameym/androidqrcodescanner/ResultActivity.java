package com.example.ameym.androidqrcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        Intent intent = getIntent();
        intent.getExtras();
        String scoreRe = intent.getStringExtra("score");
        result = (TextView) findViewById(R.id.text_result);
        result.setText(scoreRe);

        // TODO: Add timer at the result screen of quiz
        // TODO:

    }
}
