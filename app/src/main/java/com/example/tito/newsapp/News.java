package com.example.tito.newsapp;

public class News {

    private String mArticleTitle;

    private String mSectionName;

    private String mDateOfArticle;

    private String mAuthorName;

    private String mUrl;

    public News(String articleTitle, String sectionName, String dateOfArticle,String authorName, String url) {
        this.mArticleTitle = articleTitle;
        this.mSectionName = sectionName;
        this.mDateOfArticle = dateOfArticle;
        this.mAuthorName = authorName;
        this.mUrl = url;
    }

    public String getArticleTitle() {
        return mArticleTitle;
    }

    public String getDateOfArticle() {
        return mDateOfArticle;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public String getUrl() {
        return mUrl;
    }
}
