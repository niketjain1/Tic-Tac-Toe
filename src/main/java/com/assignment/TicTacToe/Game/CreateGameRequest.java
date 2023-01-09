package com.assignment.TicTacToe.Game;

import lombok.Data;

@Data
public class CreateGameRequest {
    private long player1Id;
    private long player2Id;
}
