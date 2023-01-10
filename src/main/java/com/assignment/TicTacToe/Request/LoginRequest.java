package com.assignment.TicTacToe.Request;

import lombok.Data;

@Data
public class LoginRequest {
    private int id;
    private String email;
    private String password;
}
