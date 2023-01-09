package com.assignment.TicTacToe.Game;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long player1Id;
    private long player2Id;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @Enumerated(EnumType.STRING)
    private GameResult result;

    private String board;
    private long currentPlayerId;
    private LocalDateTime lastUpdatedTime;


}

