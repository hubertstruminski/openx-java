package com.example.demo.view;

import com.example.demo.service.MainService;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class MainView {

    private MainService mainService;
    private User[] users;

    @Autowired
    public MainView(MainService mainService) {
        this.mainService = mainService;
        this.users = mainService.retrieveAndConnectData();
    }

    public void showNumberOfPosts() {
        int numberOfPOsts = mainService.computeNumberOfPosts(users);
        System.out.println("Number of posts: " + numberOfPOsts);
    }

    public void showNumberOfPostsForSpecificPerson() {
        Map<String, Integer> numberOfPostsUsers = mainService.computeNumberOfPostsForSpecificPerson(users);
        numberOfPostsUsers.entrySet().stream()
                .forEach(entry -> System.out.println("User: " + entry.getKey() + ", number of posts: " + entry.getValue()));
    }

    public void showWhetherTitleOfPostsAreUnique() {
        Set<Post> duplicatedTopics = mainService.findDuplicatedTopics(users);
        if(duplicatedTopics.size() != 0) {
            duplicatedTopics.stream().forEach(post -> System.out.println(post.getTitle()));
            return;
        }
        System.out.println("All title of posts are unique.");
    }

    public void showTheNearestLocatedUsers() {
        Map<String, String> theNearestUserLocation = mainService.getTheNearestUserLocation(users);
        theNearestUserLocation.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + ", " + entry.getValue()));
    }
}
