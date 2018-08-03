package com.example.tito.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<News> news = new ArrayList<News>();

        news.add(new News("I'm the title", "John Smith", "2018/08/01"));
        news.add(new News("I'm the title", "John Smith", "2018/08/01"));
        news.add(new News("I'm the title", "John Smith", "2018/08/01"));
        news.add(new News("I'm the title", "John Smith", "2018/08/01"));
        news.add(new News("I'm the title", "John Smith", "2018/08/01"));
        news.add(new News("I'm the title", "John Smith", "2018/08/01"));
        news.add(new News("I'm the title", "John Smith", "2018/08/01"));


        ListView listView = findViewById(R.id.list);

        final NewsAdapter adapter = new NewsAdapter(this, news);

        listView.setAdapter(adapter);
    }
}
