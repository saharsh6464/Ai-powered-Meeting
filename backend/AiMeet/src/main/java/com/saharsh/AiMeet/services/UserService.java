package com.saharsh.AiMeet.services;

import com.saharsh.AiMeet.models.User;
import com.saharsh.AiMeet.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class UserService {
    @Autowired
    private UserRepository repo;

    public ResponseEntity<?> saveUser(User user){
        return ResponseEntity.ok(repo.save(user));
    }
}
