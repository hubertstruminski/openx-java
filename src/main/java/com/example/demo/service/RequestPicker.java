package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestPicker {

    private RestTemplate restTemplate;

    public RequestPicker() {
        this.restTemplate = new RestTemplate();
    }

    public User[] retrieveUsers(String url) {
        return restTemplate.getForEntity(url, User[].class).getBody();
    }

    public Post[] retrievePosts(String url) {
        return restTemplate.getForEntity(url, Post[].class).getBody();
    }
}
