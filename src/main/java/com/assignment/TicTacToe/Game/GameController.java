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

    @GetMapping
    public List<Game> getGames(@RequestParam long userId) {
        return gameService.getGamesForUser(userId);
    }

    @PostMapping("/{gameId}/move")
    public Game makeMove(@PathVariable long gameId, @RequestBody MakeMoveRequest request) {
        return gameService.makeMove(gameId, request.getPlayerID(), request.getRow(), request.getCol());
    }
}
