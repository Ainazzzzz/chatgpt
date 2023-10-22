package com.social.media.Model;

import com.social.Model.Post;
import com.social.Model.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Test
    public void testUserConstructor() {
        User user = new User();
        assertNotNull(user);
    }
    @Test
    public void testUserBuilder() {
        User user = User.builder()
                .id(1L)
                .username("Test User")
                .build();

        assertEquals(1L, user.getId());
        assertEquals("Test User", user.getUsername());
    }

    @Test
    public void testSetFollowing() {
        User user = new User();
        User user1 = new User();
        User user2 = new User();

        List<User> following = new ArrayList<>();
        following.add(user1);
        following.add(user2);

        user.setFollowing(following);

        assertNotNull(user.getFollowing());
        assertEquals(2, user.getFollowing().size());
    }
    @Test
    public void testSetPosts() {
        User user = User.builder()
                .id(1L)
                .username("TestUser")
                .build();

        Post post1 = Post.builder()
                .id(1L)
                .title("Post 1")
                .body("Body 1")
                .author(user)
                .build();

        Post post2 = Post.builder()
                .id(2L)
                .title("Post 2")
                .body("Body 2")
                .author(user)
                .build();

        user.setPosts(List.of(post1, post2));

        assertNotNull(user.getPosts());
        assertEquals(2, user.getPosts().size());
    }

    @Test
    public void testSetLikedPosts() {
        User user = User.builder()
                .id(1L)
                .username("TestUser")
                .build();

        Post post1 = Post.builder()
                .id(1L)
                .title("Post 1")
                .body("Body 1")
                .author(user)
                .build();

        Post post2 = Post.builder()
                .id(2L)
                .title("Post 2")
                .body("Body 2")
                .author(user)
                .build();

        user.setLikedPosts(List.of(post1, post2));

        assertNotNull(user.getLikedPosts());
        assertEquals(2, user.getLikedPosts().size());
    }

    @Test
    public void testSetFollowers() {
        User user = new User();
        User user1 = new User();
        User user2 = new User();

        List<User> followers = new ArrayList<>();
        followers.add(user1);
        followers.add(user2);

        user.setFollowers(followers);

        assertNotNull(user.getFollowers());
        assertEquals(2, user.getFollowers().size());
    }

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Test User");

        assertEquals(1L, user.getId());
        assertEquals("Test User", user.getUsername());
    }

    @Test
    public void testEqualsAndHashCode() {
        Post post1 = new Post();
        post1.setTitle("Test Title");
        post1.setBody("Test Body");
        Post post2 = new Post();
        post2.setTitle("Test Title");
        post2.setBody("Test Body");
        Post post3 = new Post();
        post3.setTitle("Test Title 2");
        post3.setBody("Test Body 2");

        assertTrue(post1.equals(post2));
        assertFalse(post1.equals(post3));

        assertEquals(post1.hashCode(), post2.hashCode());

        User user1 = new User();
        user1.setUsername("Test User");
        User user2 = new User();
        user2.setUsername("Test User");
        User user3 = new User();
        user3.setUsername("Test User 2");

        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));

        assertEquals(user1.hashCode(), user2.hashCode());
    }
}
