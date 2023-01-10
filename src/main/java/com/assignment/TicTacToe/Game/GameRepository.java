package com.assignment.TicTacToe.Game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByPlayer1IdOrPlayer2Id(int player1Id, int player2Id);
    Optional<Game> findByPlayer1IdAndPlayer2Id(int player1Id, int player2Id);
}
