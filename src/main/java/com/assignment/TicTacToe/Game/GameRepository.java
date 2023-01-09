package com.assignment.TicTacToe.Game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    List<Game> findByPlayer1IdOrPlayer2Id(long player1Id, long player2Id);
    Optional<Game> findByPlayer1IdAndPlayer2Id(long player1Id, long player2Id);
}
