package com.social.Service.impl;

import com.social.Model.Post;
import com.social.Model.User;
import com.social.Repo.PostRepo;
import com.social.Repo.UserRepo;
import com.social.Service.PostService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final UserRepo userRepo;

    @Override
    public void addNewPost(Post post, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        postRepo.save(post);
        post.setAuthor(user);
        user.getPosts().add(post);
        userRepo.save(user);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepo.findById(id).orElseThrow(()->new RuntimeException("Post not found"));
    }

    @Override
    public void deletePost(Long id) {
     Post post = postRepo.findById(id).orElseThrow(()->new RuntimeException("Post not found"));
     User user = post.getAuthor();
        user.getPosts().remove(post);
        userRepo.save(user);
        postRepo.deleteById(id);

    }

    @Override
    public List<Post> getPotsByUserId(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPosts();
    }

    @Override
    public List<Post> getPostsByUserUsername(String username) {
        User user = userRepo.findByUsername(username);
       return user.getPosts();
    }

    @Override
    public void likePost(Long postId, Long userId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        post.getLikes().add(user);
        postRepo.save(post);
        user.getLikedPosts().add(post);
        userRepo.save(user);

    }

    @Override
    public void unlikePost(Long postId, Long userId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        post.getLikes().remove(user);
        postRepo.save(post);

    }

    @Override
    public List<Post> getLikedPosts(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        return user.getLikedPosts();

    }

    @Override
    public List<User> getPostLikes(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(()->new RuntimeException("Post not found"));
        return post.getLikes();
    }
}
