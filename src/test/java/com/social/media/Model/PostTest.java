package com.social.media.Model;

import com.social.Model.Post;
import com.social.Model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PostTest {

    @Test
    public void testPostConstructor() {
        Post post = new Post();
        assertNotNull(post);
    }

    @Test
    public void testPostGettersAndSetters() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setBody("Test Body");

        assertEquals(1L, post.getId());
        assertEquals("Test Title", post.getTitle());
        assertEquals("Test Body", post.getBody());
    }
    @Test
    public void testSetLikes() {
        Post post = Post.builder()
                .id(1L)
                .title("TestTitle")
                .body("TestBody")
                .build();

        User user1 = User.builder()
                .id(1L)
                .username("User1")
                .build();

        User user2 = User.builder()
                .id(2L)
                .username("User2")
                .build();

        post.setLikes(List.of(user1, user2));

        assertNotNull(post.getLikes());
        assertEquals(2, post.getLikes().size());
    }
    @Test
    public void testPostBuilder() {
        Post post = Post.builder()
                .id(1L)
                .title("Test Title")
                .body("Test Body")
                .build();

        assertEquals(1L, post.getId());
        assertEquals("Test Title", post.getTitle());
        assertEquals("Test Body", post.getBody());
    }
    @Test
    public void testPostToString() {
        User author = User.builder()
                .id(1L)
                .username("AuthorUser")
                .build();

        Post post = Post.builder()
                .id(1L)
                .title("TestTitle")
                .body("TestBody")
                .author(author)
                .build();

        String expectedToString = "Post(id=1, title=TestTitle, body=TestBody, likes=null, author=User(id=1, username=AuthorUser, following=null, followers=null, posts=null, likedPosts=null))";

        assertEquals(expectedToString, post.toString());
    }


}
