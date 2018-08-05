package com.example.tito.newsapp;

public class News {

    private String ArticleTitle;

    private String SectionName;

    private String DateOfArticle;

    private String Url;

    public News(String articleTitle, String sectionName, String dateOfArticle, String url) {
        this.ArticleTitle = articleTitle;
        this.SectionName = sectionName;
        this.DateOfArticle = dateOfArticle;
        this.Url = url;
    }

    public String getArticleTitle() {
        return ArticleTitle;
    }

    public String getDateOfArticle() {
        return DateOfArticle;
    }

    public String getSectionName() {
        return SectionName;
    }

    public String getUrl() {
        return Url;
    }
}
