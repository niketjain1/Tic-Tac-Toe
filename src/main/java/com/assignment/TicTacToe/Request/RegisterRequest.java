package com.assignment.TicTacToe.Request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterRequest {
    private int id;
    private String email;
    private String username;
    private String password;
}

