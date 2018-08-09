package com.example.tito.newsapp;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{

    private static final int NEWS_LOADER_ID = 1;
    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private ListView listView;
    private String apiKey;
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // the api key
        apiKey = BuildConfig.THE_GUARDIAN_API_KEY;

        mEmptyStateTextView = findViewById(R.id.empty_view);

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(NEWS_LOADER_ID, null,this);

        listView = findViewById(R.id.list);

        listView.setEmptyView(mEmptyStateTextView);

        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        listView.setAdapter(mAdapter);

        if(!isNetworkOnline()){
            buildDialog(MainActivity.this).show();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = mAdapter.getItem(position);

                Uri earthquakeUri = Uri.parse(currentNews.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                startActivity(websiteIntent);
            }
        });

    }
    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        String orderBySort = sharedPreferences.getString(
                String.valueOf(R.string.list_of_sorts_title),
                String.valueOf(R.string.list_of_sorts_title_key));

        String orderByDate = sharedPreferences.getString(
                String.valueOf(R.string.order_by_date_title_key),
                String.valueOf(Integer.parseInt(getString(R.string.order_by_date_title_default))));

        Uri baseUri = Uri.parse(GUARDIAN_REQUEST_URL);

        Uri.Builder uriBuilder = baseUri.buildUpon();
        // the last version of the URL
        //?q=debate&tag=politics/politics&show-tags=contributor&from-date=2014-01-01&api-
        // key=ad80fba8-0854-44c5-9525-51709949001c
        uriBuilder.appendQueryParameter("debate&tag", orderBySort);
        uriBuilder.appendQueryParameter("show-tags","contributor");
        uriBuilder.appendQueryParameter("from-date", orderByDate);
        uriBuilder.appendQueryParameter("api-key", apiKey);

        return new NewsLoader(this, uriBuilder.toString());
    }
    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> newsOfDay) {
        if(!isNetworkOnline()){
            buildDialog(MainActivity.this).show();
        } else {
            mEmptyStateTextView.setText(R.string.empty_state_text_view);
        }
        mAdapter.clear();
        if (newsOfDay != null && !newsOfDay.isEmpty()){
            mAdapter.addAll(newsOfDay);
        }
    }
    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }

    public boolean isNetworkOnline() {
        boolean status;
        try {
            ConnectivityManager cm = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                status = true;
            }else {
                    status = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return status;
    }
    public AlertDialog.Builder buildDialog(Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(getString(R.string.no_internet));
        builder.setMessage(getString(R.string.no_internet_connection));

        builder.setPositiveButton(getString(R.string.press_ok), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });
        return builder;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
