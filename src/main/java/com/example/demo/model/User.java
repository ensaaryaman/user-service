package com.example.demo.model;

public class User {

    private Long id;
    private String name;
    private String email;

    // Jackson'ın JSON'u nesneye çevirmesi için gereken parametresiz constructor
    public User() {
    }

    // Elde hazır değerlerle nesne kurmak için kolaylık constructor'ı
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
