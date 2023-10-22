package com.social.media.serviceTest;

import com.social.Model.Post;
import com.social.Model.User;
import com.social.Service.PostService;
import com.social.Service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PostServiceImplTest {

    @Qualifier("postServiceImpl")
    @Autowired
    private PostService postService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userservice;

    private User user;
    private Post post;
    @BeforeEach
    public void setUp() {

        user = new User();
        user.setUsername("Junit");
        userservice.addNewUser(user);

        post = new Post();
        post.setTitle("test");
        post.setBody("test");
        postService.addNewPost(post, user.getId());

    }

    @Test
    void getPostById() {
        assertEquals(post, postService.getPostById(post.getId()));
    }

    @Test
    void deletePost() {
        postService.deletePost(post.getId());
        Exception exception = assertThrows(RuntimeException .class, () -> {
            postService.getPostById(post.getId());
        });
        assertEquals("Post not found", exception.getMessage());
    }

    @Test
    void getPotsByUserId() {
        List<Post> actualPosts = postService.getPotsByUserId(user.getId());
        List<Post> expectedPosts = new ArrayList<>(actualPosts);
        assertEquals(expectedPosts, postService.getPotsByUserId(user.getId()));
    }

    @Test
    void getPostsByUserUsername() {
        postService.addNewPost(post, user.getId());
        List<Post> actualPosts = postService.getPostsByUserUsername(user.getUsername());
        List<Post> expectedPosts = new ArrayList<>(actualPosts);
        assertEquals(expectedPosts, postService.getPostsByUserUsername(user.getUsername()));
    }

    @Test
    void likePost() {
        postService.likePost(post.getId(), user.getId());
        List<Post> actualPosts = postService.getLikedPosts(user.getId());
        List<Post> expectedPosts = new ArrayList<>(actualPosts);
        assertEquals(expectedPosts, postService.getLikedPosts(user.getId()));
    }

    @Test
    void unlikePost() {
        postService.likePost(post.getId(), user.getId());
        postService.unlikePost(post.getId(), user.getId());
        List<Post> actualPosts = postService.getLikedPosts(user.getId());
        List<Post> expectedPosts = new ArrayList<>(actualPosts);
        expectedPosts.remove(post);
        assertFalse(expectedPosts.contains(post));
    }

    @Test
    void getLikedPosts() {
        List<Post> posts = postService.getLikedPosts(user.getId());
        List<Post> post1 = new ArrayList<>(posts);
        assertEquals(post1, postService.getLikedPosts(user.getId()));
    }

    @Test
    void getPostLikes() {
        List<User> posts = postService.getPostLikes(post.getId());
        List<User> post1 = new ArrayList<>(posts);
        assertEquals(post1, postService.getPostLikes(post.getId()));
    }


}
