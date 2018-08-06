package com.example.tito.newsapp;

public class News {

    private String ArticleTitle;

    private String SectionName;

    private String DateOfArticle;

    private String AuthorName;

    private String Url;

    public News(String articleTitle, String sectionName, String dateOfArticle,String authorName, String url) {
        this.ArticleTitle = articleTitle;
        this.SectionName = sectionName;
        this.DateOfArticle = dateOfArticle;
        this.AuthorName = authorName;
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

    public String getAuthorName() {
        return AuthorName;
    }

    public String getUrl() {
        return Url;
    }
}
