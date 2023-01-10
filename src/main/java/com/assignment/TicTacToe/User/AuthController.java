package com.assignment.TicTacToe.User;

import com.assignment.TicTacToe.Game.CreateGameRequest;
import com.assignment.TicTacToe.Game.Game;
import com.assignment.TicTacToe.Game.GameService;
import com.assignment.TicTacToe.Request.LoginRequest;
import com.assignment.TicTacToe.Request.RegisterRequest;
import com.assignment.TicTacToe.User.User;
import com.assignment.TicTacToe.User.UserService;
import org.springframework.web.bind.annotation.*;

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
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.login(request.getEmail(), request.getPassword());
    }


}