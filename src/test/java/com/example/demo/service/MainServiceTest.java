package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MainServiceTest {

    private MainService mainService;
    private User[] users;

    @Autowired
    public MainServiceTest(MainService mainService) {
        this.mainService = mainService;
        users = mainService.retrieveAndConnectData();
    }

    @Test
    public void testIfDataAreCorrectConnected() {
        User[] users = mainService.retrieveAndConnectData();

        assertEquals(10, users[0].getPosts().size());
        assertEquals(10, users[1].getPosts().size());
        assertEquals(10, users[2].getPosts().size());
    }

    @Test
    public void testIfMethodComputeCorrectlyNumberOfPosts() {
        int numberOfPosts = mainService.computeNumberOfPosts(users);

        assertEquals(100, numberOfPosts);
    }

    @Test
    public void testIfMethodComputeCorrectlyNumberOfPostsForSpecificPerson() {
        Map<String, Integer> actualMap = mainService.computeNumberOfPostsForSpecificPerson(users);

        actualMap.entrySet().stream().forEach(entry -> assertEquals(10, entry.getValue()));
    }

    @Test
    public void testIfTitlesAreDuplicated() {
        User[] users = {
                new User(1, "", "", "...", null, "", "", null,
                        Arrays.asList(
                                new Post(1, 1, "Title", ""),
                                new Post(1, 2, "Title", "")
                        )),
                new User(2, "", "", "", null, "", "", null,
                        Arrays.asList(
                                new Post(2, 3, "Comparator", ""),
                                new Post(2, 4, "Comparator", "")
                        )),
                new User(3, "", "", "", null, "", "", null,
                        Arrays.asList(
                                new Post(3, 5, "Title2", "")
                        ))};

        Set<Post> actualDuplicatedTopics = mainService.findDuplicatedTopics(users);

        assertEquals(2, actualDuplicatedTopics.size());
    }

    @Test
    public void testIfMethodReturnsCorrectNumberOfPostsFromUserContext() {
        List<Post> posts = mainService.retrievePostsFromUsers(users);

        assertEquals(100, posts.size());
    }

    @Test
    public void testIfDistanceIsComputedCorrectly() {
        double actualDistance = MainService.computeDistance(10, 10, 20, 20);
        double expectedDistance = 1544.6832414008247;

        assertEquals(expectedDistance, actualDistance);
    }
}
