package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    // Verimizin tutulduğu yer (DB yok — uygulama kapanınca silinir)
    private final List<User> users = new ArrayList<>();

    // Thread-safe id sayacı; ilk incrementAndGet() -> 1
    private final AtomicLong counter = new AtomicLong();

    // Tüm kullanıcıları döndür
    public List<User> findAll() {
        return users;
    }

    // Yeni kullanıcı ekle: id'yi sunucu üretir
    public User save(User user) {
        user.setId(counter.incrementAndGet());
        users.add(user);
        return user;
    }

    // id'ye göre ara; bulunamazsa boş Optional
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst();
    }

    // Varsa name/email güncelle, güncellenen kullanıcıyı döndür; yoksa boş Optional
    public Optional<User> update(Long id, User updated) {
        return findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setEmail(updated.getEmail());
            return existing;
        });
    }

    // Sil; gerçekten silindiyse true, id yoksa false
    public boolean deleteById(Long id) {
        return users.removeIf(u -> id.equals(u.getId()));
    }
}
