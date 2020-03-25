package com.example.demo.service;

import com.example.demo.model.Geo;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainService {

    @Autowired
    private RequestPicker requestPicker;

    public User[] retrieveAndConnectData() {
        User[] users = requestPicker.retrieveUsers("https://jsonplaceholder.typicode.com/users");
        Post[] posts = requestPicker.retrievePosts("https://jsonplaceholder.typicode.com/posts");

        for(User user: users) {
            List<Post> postsUserList = new ArrayList<>();
            for(Post post: posts) {
                if(user.getId() == post.getUserId()) {
                   postsUserList.add(post);
                }
            }
            user.setPosts(postsUserList);
        }
        return users;
    }

    public int computeNumberOfPosts(User[] users) {
        return Arrays.stream(users).mapToInt(user -> user.getPosts().size()).sum();
    }

    public Map<String, Integer> computeNumberOfPostsForSpecificPerson(User[] users) {
        return Arrays.stream(users).collect(Collectors.toMap(x -> x.getUsername(), x -> x.getPosts().size()));
    }

    public Set<Post> findDuplicatedTopics(User[] users) {
        List<Post> posts = retrievePostsFromUsers(users);

        Set<Post> duplicates = new LinkedHashSet<>();
        for(Post post1: posts) {
            OUTER_LOOP:
            for(Post post2: posts) {
                if(post1.equals(post2)) {
                    continue;
                }
                for(Post duplicatePost: duplicates) {
                    if(duplicatePost.getTitle().equals(post1.getTitle())) {
                        break OUTER_LOOP;
                    }
                }
                if(post1.getTitle().equals(post2.getTitle())) {
                    duplicates.add(post1);
                    break OUTER_LOOP;
                }
            }
        }
        return duplicates;
    }

    public List<Post> retrievePostsFromUsers(User[] users) {
        return Arrays.stream(users).flatMap(e -> e.getPosts().stream()).collect(Collectors.toList());
    }

    public Map<String, String> getTheNearestUserLocation(User[] users) {
        Map<String, String> result = new HashMap<>();
        for(User user1: users) {
            Geo user1Coordinates = user1.getAddress().getGeo();
            TreeMap<Double, String> theNearestUsers = new TreeMap<>();
            for(User user2: users) {
                if(user2.getId() == user1.getId()) {
                    continue;
                }
                Geo user2Coordinates = user2.getAddress().getGeo();
                double distance = computeDistance(user1Coordinates.getLat(), user1Coordinates.getLng(),
                        user2Coordinates.getLat(), user2Coordinates.getLng());

                theNearestUsers.put(distance, user2.getUsername());
            }
            result.put(user1.getUsername(), theNearestUsers.firstEntry().getValue() + " around " +
                    theNearestUsers.firstEntry().getKey() + " km");
        }
        return result;
    }

    public static double computeDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        if ((latitude1 == latitude2) && (longitude1 == longitude2)) {
            return 0;
        }
        else {
            double theta = longitude1 - longitude2;
            double distance = Math.sin(Math.toRadians(latitude1)) * Math.sin(Math.toRadians(latitude2)) +
                    Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.cos(Math.toRadians(theta));
            distance = Math.acos(distance);
            distance = Math.toDegrees(distance);
            distance = distance * 60 * 1.1515;
            return distance * 1.609344;

        }
    }
}
