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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        News currentNews = getItem(position);

        TextView articleTitle = convertView.findViewById(R.id.articleTitle);
        articleTitle.setText(currentNews.getArticleTitle());

        TextView sectionName = convertView.findViewById(R.id.sectionName);
        sectionName.setText(currentNews.getSectionName());

        TextView authorName = convertView.findViewById(R.id.author_name);
        authorName.setText(currentNews.getAuthorName());

        TextView articleDate = convertView.findViewById(R.id.articleDate);
        articleDate.setText(currentNews.getDateOfArticle());

       return convertView;
    }

}
