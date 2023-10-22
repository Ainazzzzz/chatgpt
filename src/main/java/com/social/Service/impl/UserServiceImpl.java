package com.social.Service.impl;

import com.social.Model.User;
import com.social.Repo.UserRepo;
import com.social.Service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepo userRepo;
    @Override
    public void addNewUser(User user) {
         userRepo.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    @Override
    public User getUserByUsername(String username) {
    return userRepo.findByUsername(username);
    }

    @Override
    public void followUser(Long userId, Long followerId) {
      User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
      User follower = userRepo.findById(followerId).orElseThrow(() -> new RuntimeException("Follower not found"));

      user.getFollowers().add(follower);
      follower.getFollowing().add(user);

      userRepo.save(user);
      userRepo.save(follower);

    }

    @Override
    public void unfollowUser(Long userId, Long followerId) {
    User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
    User follower = userRepo.findById(followerId).orElseThrow(()->new RuntimeException("User not found"));
    user.getFollowers().remove(follower);
    follower.getFollowing().remove(user);
    }

    @Override
    public void deleteUser(Long id) {
    userRepo.deleteById(id);
    }

    @Override
    public List<User> getFollowers(Long id) {
        User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return user.getFollowers();
    }

    @Override
    public List<User> getFollowing(Long id) {
     User user = userRepo.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return user.getFollowing();
    }
}
