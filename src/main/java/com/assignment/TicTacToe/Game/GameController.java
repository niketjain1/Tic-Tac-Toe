package com.assignment.TicTacToe.Game;

import com.assignment.TicTacToe.Request.*;
import com.assignment.TicTacToe.Request.MakeMoveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    @Autowired
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/user/{userId}")
    public List<Game> getGames(@PathVariable int userId) {
        return gameService.getGamesForUser(userId);
    }
    @PostMapping("/create-game")
    public Game createGame(@RequestBody CreateGameRequest request) {
        return gameService.createGame(request.getPlayer1Id(), request.getPlayer2Id());
    }
    @PutMapping("/{gameId}/move")
    public Game makeMove(@PathVariable int gameId, @RequestBody MakeMoveRequest request) {
        return gameService.makeMove(gameId, request.getPlayerID(), request.getRow(), request.getCol());
    }
}
