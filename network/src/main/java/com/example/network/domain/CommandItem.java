package com.example.network.domain;

public class CommandItem {
    private String articleId;
    private String commentContent;

    public CommandItem(String articleId, String commentContent) {
        this.articleId = articleId;
        this.commentContent = commentContent;
    }

    @Override
    public String toString() {
        return "CommandItem{" +
                "articleId='" + articleId + '\'' +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
