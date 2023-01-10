package com.assignment.TicTacToe.Request;

import lombok.Data;

@Data
public class MakeMoveRequest {
    private int playerID;
    private int row;
    private int col;
}
