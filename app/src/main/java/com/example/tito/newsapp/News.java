package com.example.tito.newsapp;

public class News {

    private String ArticleTitle;

    private String AuthorName;

    private String DateOfArticle;

    public News(String articleTitle, String authorName, String dateOfArticle) {
        this.ArticleTitle = articleTitle;
        this.AuthorName = authorName;
        this.DateOfArticle = dateOfArticle;
    }

    public String getArticleTitle() {
        return ArticleTitle;
    }

    public String getDateOfArticle() {
        return DateOfArticle;
    }

    public String getAuthorName() {
        return AuthorName;
    }
}
