package com.assignment.TicTacToe.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    @JsonIgnore
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
