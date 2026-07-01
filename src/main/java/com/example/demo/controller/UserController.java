package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    // GET /users/{id} -> varsa 200 + kullanıcı, yoksa 404
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT /users/{id} -> varsa güncelle (200), yoksa 404
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return service.update(id, user)
                .map(updated -> ResponseEntity.ok(updated))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /users/{id} -> silindiyse 204 (gövdesiz), yoksa 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
