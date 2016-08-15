package com.udacity.gradle.laughactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LaughActivity extends AppCompatActivity {
    public static final String JOKE_EXTRA_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laugh);

        Intent incomingIntent = getIntent();
        String jokeText;
        if (incomingIntent != null &&
                (jokeText = incomingIntent.getStringExtra(JOKE_EXTRA_KEY)) != null) {
            ((TextView) findViewById(R.id.joke_goes_here)).setText(jokeText);
        }
    }
}
