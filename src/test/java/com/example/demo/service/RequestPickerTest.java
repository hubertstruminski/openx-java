package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RequestPickerTest {

    private RequestPicker requestPicker;

    @Autowired
    public RequestPickerTest(RequestPicker requestPicker) {
        this.requestPicker = requestPicker;
    }

    @Test
    public void testIfMethodReturnsCorrectNumberOfPosts() {
        Post[] posts = requestPicker.retrievePosts("https://jsonplaceholder.typicode.com/posts");
        assertEquals(100, posts.length);
    }

    @Test
    public void testIfMethodReturnsCorrectNumberOfUsers() {
        User[] users = requestPicker.retrieveUsers("https://jsonplaceholder.typicode.com/users");
        assertEquals(10, users.length);
    }
}
