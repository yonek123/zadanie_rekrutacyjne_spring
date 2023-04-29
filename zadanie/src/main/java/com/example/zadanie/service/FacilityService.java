package com.example.zadanie.service;

import com.example.zadanie.model.Facility;
import com.example.zadanie.model.User;
import com.example.zadanie.repository.FacilityCollectionRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface FacilityService {
    public List<Facility> findAll();

    public Facility findById(Integer id);

    public void create(Facility facility);

    public Facility update(Integer id, Facility facility);

    public void delete(Integer id);

    public Facility findByName(String name);
}
