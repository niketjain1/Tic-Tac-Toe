package com.assignment.TicTacToe.Game;

import com.assignment.TicTacToe.User.User;
import com.assignment.TicTacToe.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    public GameService(GameRepository gameRepository, UserRepository userRepository){
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }
    public Game findById(int id) {
        return gameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid game id"));
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }
    public List<Game> getGamesForUser(int userId) {
        return gameRepository.findByPlayer1IdOrPlayer2Id(userId, userId);
    }

    public Game createGame(int player1Id, int player2Id) {
        User player1 = userRepository.findById(player1Id).orElseThrow(() -> new IllegalArgumentException("Invalid player1 id"));
        User player2 = userRepository.findById(player2Id).orElseThrow(() -> new IllegalArgumentException("Invalid player2 id"));
        Game game = new Game();
        game.setPlayer1Id(player1Id);
        game.setPlayer2Id(player2Id);
        game.setStatus(GameStatus.NEW_GAME);
        game.setBoard("---------");
        game.setCurrentPlayerId(player1Id);
        game.setLastUpdatedTime(LocalDateTime.now());
        return gameRepository.save(game);
    }

    public Game makeMove(int gameId, int playerID, int row, int col){
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Invalid game id"));
        if(game == null) {
            throw new IllegalArgumentException("Invalid game id");
        }
        if (game.getResult() != null) {
            throw new IllegalStateException("Game has already finished");
        }
        if (game.getCurrentPlayerId() != playerID) {
            throw new IllegalStateException("It's not your turn");
        }
        int index = 3 * row + col;
        StringBuilder sb = new StringBuilder(game.getBoard());
        sb.setCharAt(index, game.getCurrentPlayerId() == game.getPlayer1Id() ? 'X' : 'O');
        game.setBoard(sb.toString());
        game.setCurrentPlayerId(game.getCurrentPlayerId() == game.getPlayer1Id() ? game.getPlayer2Id() : game.getPlayer1Id());
        game.setLastUpdatedTime(LocalDateTime.now());
        checkGameResult(game);
        return gameRepository.save(game);
    }

    public void checkGameResult(Game game) {
        String board = game.getBoard();
        // check rows
        for (int i = 0; i < 3; i++) {
            if (board.charAt(3 * i) == board.charAt(3 * i + 1) && board.charAt(3 * i + 1) == board.charAt(3 * i + 2)) {
                game.setResult(board.charAt(3 * i) == 'X' ? GameResult.X_WON : GameResult.O_WON);
                return;
            }
        }
        // check columns
        for (int i = 0; i < 3; i++) {
            if (board.charAt(i) == board.charAt(i + 3) && board.charAt(i + 3) == board.charAt(i + 6)) {
                game.setResult(board.charAt(i) == 'X' ? GameResult.X_WON : GameResult.O_WON);
                return;
            }
        }
        // check diagonals
        if (board.charAt(0) == board.charAt(4) && board.charAt(4) == board.charAt(8)) {
            game.setResult(board.charAt(0) == 'X' ? GameResult.X_WON : GameResult.O_WON);
            return;
        }
        if (board.charAt(2) == board.charAt(4) && board.charAt(4) == board.charAt(6)) {
            game.setResult(board.charAt(2) == 'X' ? GameResult.X_WON : GameResult.O_WON);
            return;
        }
        // check for draw
        if (board.chars().noneMatch(ch -> ch == '-')) {
            game.setResult(GameResult.DRAW);
        }
    }
}
