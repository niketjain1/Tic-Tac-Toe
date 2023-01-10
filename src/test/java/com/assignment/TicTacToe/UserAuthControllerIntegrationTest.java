package com.assignment.TicTacToe;

import com.assignment.TicTacToe.Game.GameService;
import com.assignment.TicTacToe.User.AuthController;
import com.assignment.TicTacToe.User.User;
import com.assignment.TicTacToe.User.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(AuthController.class)
public class UserAuthControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private UserService userService;

    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_EMAIL = "testuser@email.com";
    private static final String TEST_PASSWORD = "testpassword";
    private static final int TEST_USER_ID = 1;

    @Test
    public void testRegiserUser() throws Exception{
        User testUser = new User();
        testUser.setId(TEST_USER_ID);
        testUser.setUsername(TEST_USERNAME);
        testUser.setEmail(TEST_EMAIL);
        testUser.setPassword(TEST_PASSWORD);

        when(userService.register(testUser)).thenReturn(testUser);

        mockMvc.perform(post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(testUser)))
                .andExpect(status().isOk());
    }
    @Test
    public void testLoginUser() throws Exception{
        User testUser = new User(TEST_EMAIL, TEST_PASSWORD);

        when(userService.login(testUser.getEmail(), testUser.getPassword())).thenReturn(testUser);

        mockMvc.perform(get("/auth/login")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(testUser)))
                .andExpect(status().isOk());

    }

}
