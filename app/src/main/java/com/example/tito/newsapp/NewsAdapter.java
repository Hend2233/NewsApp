package com.example.tito.newsapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Activity context, List<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_of_news, parent, false);
        }

        News currentNews = getItem(position);

        TextView articleTitle = convertView.findViewById(R.id.articleTitle);
        articleTitle.setText(currentNews.getArticleTitle());

        TextView authorName = convertView.findViewById(R.id.authorName);
        authorName.setText(currentNews.getAuthorName());

        TextView articleDate = convertView.findViewById(R.id.articleDate);
        articleDate.setText(currentNews.getDateOfArticle());

       return convertView;
    }

}
