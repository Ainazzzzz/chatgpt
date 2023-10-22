package com.social.media.ControllerTest;

import com.social.Controller.PostController;
import com.social.Model.Post;
import com.social.Service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void testAddNewPost() throws Exception {

        Mockito.doNothing().when(postService).addNewPost(Mockito.any(Post.class), Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/post/add/1")
                        .content("{\n" +
                                "    \"title\": \"test\",\n" +
                                "    \"body\": \"test\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPostById() throws Exception {

        Post mockPost = new Post();
        Mockito.when(postService.getPostById(Mockito.anyLong())).thenReturn(mockPost);

        mockMvc.perform(MockMvcRequestBuilders.get("/post/getPost/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeletePost() throws Exception {
        Mockito.doNothing().when(postService).deletePost(Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/post/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPostsByUserId() throws Exception {
        List<Post> mockPosts = Collections.singletonList(new Post());
        Mockito.when(postService.getPotsByUserId(Mockito.anyLong())).thenReturn(mockPosts);

        mockMvc.perform(MockMvcRequestBuilders.get("/post/getPostByUser/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPostsByUserUsername() throws Exception {
        List<Post> mockPosts = Collections.singletonList(new Post());
        Mockito.when(postService.getPostsByUserUsername(Mockito.anyString())).thenReturn(mockPosts);

        mockMvc.perform(MockMvcRequestBuilders.get("/post/getPostsByUsername/test"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testLikePost() throws Exception {
        Mockito.doNothing().when(postService).likePost(Mockito.anyLong(), Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.post("/post/like/1/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUnlikePost() throws Exception {
        Mockito.doNothing().when(postService).unlikePost(Mockito.anyLong(), Mockito.anyLong());

        mockMvc.perform(MockMvcRequestBuilders.post("/post/unlike/1/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetLikedPosts() throws Exception {
        List<Post> mockPosts = Collections.singletonList(new Post());
        Mockito.when(postService.getLikedPosts(Mockito.anyLong())).thenReturn(mockPosts);

        mockMvc.perform(MockMvcRequestBuilders.get("/post/getLiked/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPostLikes() throws Exception {
        Mockito.when(postService.getPostLikes(Mockito.anyLong())).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/post/getLikes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
