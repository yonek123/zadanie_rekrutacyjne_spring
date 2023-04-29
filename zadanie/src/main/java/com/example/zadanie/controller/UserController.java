package com.example.zadanie.controller;


import com.example.zadanie.model.User;
import com.example.zadanie.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        User u = userService.findById(id);
        if (u == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        else
            return u;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody User user) {
        userService.create(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @Valid @RequestBody User user) {
        if (id != user.userId())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request!");
        else if (userService.update(id, user) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (userService.findById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        else
            userService.delete(id);
    }
}
