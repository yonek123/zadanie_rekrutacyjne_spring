package com.example.zadanie.service;

import com.example.zadanie.model.User;

import java.util.List;

public interface UserService {
    public abstract List<User> findAll();

    public abstract User findById(Integer id);

    public abstract void create(User user);

    public abstract User update(Integer id, User user);

    public abstract void delete(Integer id);

    public User findByName(String name);
}
