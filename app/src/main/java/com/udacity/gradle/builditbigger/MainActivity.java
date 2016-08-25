package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.jokecloud.myApi.MyApi;
import com.udacity.gradle.laughactivity.LaughActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static MyApi myApiService = null;
    protected ProgressBar mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSpinner = (ProgressBar) findViewById(R.id.jokeSpinner);
        mSpinner.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){
        mSpinner.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask().execute(this);
    }

    protected static class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
        private MainActivity mContext;

        @Override
        protected String doInBackground(Context... params) {
            if (myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        .setRootUrl("https://rbjokecloud.appspot.com/_ah/api/");

                myApiService = builder.build();
            }

            mContext = (MainActivity) params[0];

            try {
                return myApiService.getAJoke().execute().getData();
            } catch(IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(mContext, LaughActivity.class);
            intent.putExtra(LaughActivity.JOKE_EXTRA_KEY, result);

            mContext.mSpinner.setVisibility(View.INVISIBLE);
            mContext.startActivity(intent);
        }
    }
}
