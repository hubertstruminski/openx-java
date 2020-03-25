package com.example.demo.model;

import java.util.List;

public class User {

    private long id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
    private List<Post> posts;

    public User() {

    }

    public User(long id, String name, String username, String email, Address address, String phone, String website,
                Company company, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Address getAddress() {
        return address;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
