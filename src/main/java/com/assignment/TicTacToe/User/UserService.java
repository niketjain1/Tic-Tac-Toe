package com.assignment.TicTacToe.User;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User register(User user) {
        return userRepository.save(user);
    }
    public User loadUserByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("Invalid Username"));
    }
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    }
}
