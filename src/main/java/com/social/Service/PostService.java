package com.social.Service;

import com.social.Model.Post;
import com.social.Model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    void addNewPost(Post post, Long userId);
    Post getPostById(Long id);
    void deletePost(Long id);
    List<Post> getPotsByUserId(Long id);
    List<Post> getPostsByUserUsername(String username);
    void likePost(Long postId, Long userId);
    void unlikePost(Long postId, Long userId);
    List<Post> getLikedPosts(Long userId);
    List<User> getPostLikes(Long postId);
}
