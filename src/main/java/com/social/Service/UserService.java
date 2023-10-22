package com.social.Service;

import com.social.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    void addNewUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
    void followUser(Long userId, Long followerId);
    void unfollowUser(Long userId, Long followerId);
    void deleteUser(Long id);
    List<User> getFollowers(Long id);
    List<User> getFollowing(Long id);



}
