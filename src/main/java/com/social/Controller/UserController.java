package com.social.Controller;

import com.social.Model.User;
import com.social.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/follow/{userId}/{followerId}")
    public void followUser(@PathVariable Long userId,@PathVariable Long followerId) {
        userService.followUser(userId, followerId);
    }
    @PostMapping("/unfollow/{userId}/{followerId}")
    public void unfollowUser(@PathVariable Long userId,@PathVariable Long followerId) {
        userService.unfollowUser(userId, followerId);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
    @GetMapping("/followers/{id}")
    public List<User> getFollowers(@PathVariable Long id) {
       return userService.getFollowers(id);
    }
    @GetMapping("/following/{id}")
    public List<User> getFollowing(@PathVariable Long id) {
      return userService.getFollowing(id);
    }
    @PostMapping("/add")
    public void addNewUser(@RequestBody User user) {
        userService.addNewUser(user);
    }
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id) {
       return userService.getUserById(id);
    }
    @GetMapping("/getUser/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

}
