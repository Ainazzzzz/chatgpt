package com.social.Controller;
import com.social.Model.Post;
import com.social.Model.User;
import com.social.Service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/add/{userId}")
    public void addNewPost(@RequestBody Post post,@PathVariable Long userId){
        postService.addNewPost(post, userId);
    }
    @GetMapping("/getPost/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
    @GetMapping("/getPostByUser/{id}")
    public List<Post> getPotsByUserId(@PathVariable Long id) {
       return postService.getPotsByUserId(id);
    }
    @GetMapping("/getPostsByUsername/{username}")
    public List<Post> getPostsByUserUsername(@PathVariable String username) {
       return postService.getPostsByUserUsername(username);
    }
    @PostMapping("/like/{postId}/{userId}")
    public void likePost(@PathVariable Long postId,@PathVariable Long userId) {
        postService.likePost(postId, userId);
    }
    @PostMapping("/unlike/{postId}/{userId}")
    public void unlikePost(@PathVariable Long postId,@PathVariable Long userId) {
        postService.unlikePost(postId, userId);
    }
    @GetMapping("/getLiked/{userId}")
    public List<Post> getLikedPosts(@PathVariable Long userId) {
      return postService.getLikedPosts(userId);
    }
    @GetMapping("/getLikes/{postId}")
    public List<User> getPostLikes(@PathVariable Long postId) {
         return postService.getPostLikes(postId);
    }

}
