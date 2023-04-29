package com.example.zadanie.service;

import com.example.zadanie.model.Facility;
import com.example.zadanie.repository.FacilityCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    private FacilityCollectionRepository repository;

    public List<Facility> findAll() {
        return repository.findAll();
    }

    public Facility findById(Integer id) {
        Facility f = repository.findById(id);
        return f;
    }

    public void create(Facility facility) {
        repository.save(facility);
    }

    public Facility update(Integer id, Facility facility) {
        if (repository.findById(id) == null)
            return null;
        else {
            repository.update(facility);
            return facility;
        }
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

    public Facility findByName(String name) {
        Facility f = repository.findByName(name);
        return f;
    }
}
