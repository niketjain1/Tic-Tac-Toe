package com.assignment.TicTacToe.Request;

import com.assignment.TicTacToe.Game.CreateGameRequest;
import com.assignment.TicTacToe.Game.Game;
import com.assignment.TicTacToe.Game.GameService;
import com.assignment.TicTacToe.User.User;
import com.assignment.TicTacToe.User.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final GameService gameService;

    public AuthController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.register(request.getEmail(), request.getUsername(), request.getPassword());
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request.getEmail(), request.getPassword());
    }

    @PostMapping("/create-game")
    public Game createGame(@RequestBody CreateGameRequest request) {
        return gameService.createGame(request.getPlayer1Id(), request.getPlayer2Id());
    }
}