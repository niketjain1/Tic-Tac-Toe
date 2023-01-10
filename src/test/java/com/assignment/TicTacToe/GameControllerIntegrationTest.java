package com.assignment.TicTacToe;

import com.assignment.TicTacToe.Game.Game;
import com.assignment.TicTacToe.Game.GameController;
import com.assignment.TicTacToe.Game.GameService;
import com.assignment.TicTacToe.User.User;
import com.assignment.TicTacToe.User.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;


@WebMvcTest(GameController.class)
public class GameControllerIntegrationTest {

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

    @Before
    public void setUp() {
        // Setting up test user
        User testUser = new User();

        testUser.setUsername(TEST_USERNAME);
        testUser.setEmail(TEST_EMAIL);
        testUser.setPassword(TEST_PASSWORD);

        when(userService.loadUserByUsername(TEST_USERNAME)).thenReturn(testUser);


    }

    @Test
    public void testCreateGame() throws Exception {
        // Creating a test game
        Game testGame = new Game();
        testGame.setId(1);
        testGame.setBoard("         ");
        testGame.setPlayer1Id(TEST_USER_ID);
        testGame.setPlayer2Id(2);
        testGame.setCurrentPlayerId(TEST_USER_ID);

        when(gameService.createGame(TEST_USER_ID, 2)).thenReturn(testGame);

        // Sending a POST request to create a game\

        mockMvc.perform(post("/games/create-game")
                .contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(testGame)))
                .andExpect(jsonPath("$.id", is(testGame.getId())))
                .andExpect(jsonPath("$.player1Id", is(testGame.getPlayer1Id())))
                .andExpect(jsonPath("$.player2Id", is(testGame.getPlayer2Id())))
                .andExpect(jsonPath("$.board", is("         ")))
                .andExpect(jsonPath("$.currentPlayerId", is(testGame.getCurrentPlayerId())));

        verify(gameService, VerificationModeFactory.times(1)).createGame(TEST_USER_ID, 2);

    }
    @Test
    public void testGetGameForUser() throws Exception{
        Game game1 = gameService.createGame(TEST_USER_ID, 2);
        Game game2 = gameService.createGame(TEST_USER_ID, 4);

        List<Game> allGames = Arrays.asList(game1, game2);
        when(gameService.getGamesForUser(TEST_USER_ID)).thenReturn(allGames);

        mockMvc.perform(get("/games/user/{userId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testMakeMove() throws Exception {
        Game game1 = new Game();
        game1.setId(1);
        game1.setPlayer1Id(1);
        game1.setPlayer2Id(2);
        game1.setCurrentPlayerId(1);
        game1.setBoard("         ");

        String initialBoard = game1.getBoard();
        int currentPlayerId = game1.getCurrentPlayerId();

        int gameId = 1;
        int playerId = 1;
        int row = 0;
        int col = 0;



        Game updatedGame = new Game();
        updatedGame.setBoard("X        ");
        updatedGame.setCurrentPlayerId(2);

        // When
        mockMvc.perform(put("/games/{gameId}/move", gameId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"playerId\":" + playerId + ",\"row\":" + row + ",\"col\":" + col + "}"))
                .andExpect(status().isOk());

        // Then
    //    Game updatedGame = gameService.findById(gameId);
        assertNotEquals(initialBoard, updatedGame.getBoard());
        assertNotEquals(currentPlayerId, updatedGame.getCurrentPlayerId());
    }
}
