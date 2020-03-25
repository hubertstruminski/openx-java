package com.example.demo.model;

public class Post {

    private long userId;
    private long id;
    private String title;
    private String body;

    public Post() {

    }

    public Post(long userId, long id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }
}
