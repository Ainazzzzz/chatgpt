package com.social.media.ControllerTest;

import com.social.Controller.UserController;
import com.social.Model.User;
import com.social.Service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    public void testAddNewUser() throws Exception {
        Mockito.doNothing().when(userService).addNewUser(Mockito.any(User.class));
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/add")
                        .content("{\n" +
                                "    \"username\": \"test\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testGetUserById() throws Exception {
        Mockito.when(userService.getUserById(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetUserByUsername() throws Exception {
        Mockito.when(userService.getUserByUsername(Mockito.anyString())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUser/test"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFollowUser() throws Exception {
        Mockito.doNothing().when(userService).followUser(Mockito.anyLong(), Mockito.anyLong());
        mockMvc.perform(MockMvcRequestBuilders.post("/user/follow/1/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUnfollowUser() throws Exception {
        Mockito.doNothing().when(userService).unfollowUser(Mockito.anyLong(), Mockito.anyLong());
        mockMvc.perform(MockMvcRequestBuilders.post("/user/unfollow/1/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteUser(Mockito.anyLong());
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/delete/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testGetFollowers() throws Exception {
        Mockito.when(userService.getFollowers(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/followers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetFollowing() throws Exception {
        Mockito.when(userService.getFollowing(Mockito.anyLong())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/user/following/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




}
