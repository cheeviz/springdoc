package com.example.springdoc.controller;

import com.example.springdoc.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getUsers() {
        return users;
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        users.add(newUser);
        return newUser;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User updateUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setNome(updateUser.getNome());
                user.setEmail(updateUser.getEmail());

                return user;
            }
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return "Usuário deletado com sucesso.";
            }
        }

        return "Usuário não encontrado!";
    }
}
