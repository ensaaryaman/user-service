package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    // Constructor injection: Spring UserService bean'ini buraya geçirir
    public UserController(UserService service) {
        this.service = service;
    }

    // GET /users -> tüm kullanıcılar, 200 OK
    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    // POST /users -> yeni kullanıcı ekle, 201 Created
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User saved = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
