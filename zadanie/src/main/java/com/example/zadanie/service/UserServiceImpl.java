package com.example.zadanie.service;

import com.example.zadanie.model.User;
import com.example.zadanie.repository.UserCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserCollectionRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Integer id) {
        User u = repository.findById(id);
        return u;
    }

    public void create(User user) {
        repository.save(user);
    }

    public User update(Integer id, User user) {
        if (repository.findById(id) == null)
            return null;
        else {
            repository.update(user);
            return user;
        }
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

    public User findByName(String name) {
        User u = repository.findByName(name);
        return u;
    }
}
