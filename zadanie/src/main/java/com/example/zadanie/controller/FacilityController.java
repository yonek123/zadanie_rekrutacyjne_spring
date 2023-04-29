package com.example.zadanie.controller;

import com.example.zadanie.model.Facility;
import com.example.zadanie.service.FacilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/facility")
public class FacilityController {
    @Autowired
    FacilityService facilityService;

    @GetMapping("")
    public List<Facility> findAll() {
        return facilityService.findAll();
    }

    @GetMapping("/{id}")
    public Facility findById(@PathVariable Integer id) {
        Facility f = facilityService.findById(id);
        if (f == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found!");
        else
            return f;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Facility facility) {
        facilityService.create(facility);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @Valid @RequestBody Facility facility) {
        if (id != facility.facilityId())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request!");
        else if (facilityService.update(id, facility) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found!");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (facilityService.findById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Facility not found!");
        else
            facilityService.delete(id);
    }
}
