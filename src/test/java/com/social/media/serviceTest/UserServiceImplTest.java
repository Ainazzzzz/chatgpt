package com.social.media.serviceTest;
import com.social.Model.User;
import com.social.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class UserServiceImplTest {
    @Qualifier("userServiceImpl")
    @Autowired
 private UserService userservice;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUsername("test");
        userservice.addNewUser(user);
    }

    @Test
    void getUserById() {
        assertEquals(user, userservice.getUserById(user.getId()));

    }

    @Test
    void getUserByUsername() {
        assertEquals(user, userservice.getUserByUsername(user.getUsername()));

    }

    @Test
    void followUser() {
      User follower = new User();
      user.setUsername("follower");
      userservice.addNewUser(follower);
      userservice.followUser(user.getId(), follower.getId());
      List<User> followers = userservice.getFollowers(user.getId());
        assertTrue(followers.contains(follower));
    }

    @Test
    void unfollowUser() {
        User follower = new User();
        user.setUsername("follower");
        userservice.addNewUser(follower);
        userservice.followUser(user.getId(), follower.getId());
        userservice.unfollowUser(user.getId(), follower.getId());
        List<User> followers = userservice.getFollowers(user.getId());
        assertFalse(followers.contains(follower));

    }

    @Test
    void deleteUser() {
        userservice.deleteUser(user.getId());
        Exception exception = assertThrows(RuntimeException .class, () -> {
            userservice.getUserById(user.getId());
        });
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    void getFollowers() {
       List<User> followers = userservice.getFollowers(user.getId());
        assertEquals(followers, userservice.getFollowers(user.getId()));
    }

    @Test
    void getFollowing() {
      List <User> following = userservice.getFollowing(user.getId());
        assertEquals(following, userservice.getFollowing(user.getId()));

    }


}

