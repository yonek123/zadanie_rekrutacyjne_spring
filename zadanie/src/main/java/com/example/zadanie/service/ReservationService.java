package com.example.zadanie.service;

import com.example.zadanie.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ReservationService {
    public List<Reservation> findAll();

    public Reservation findById(Integer id);

    public boolean checkIfFacilityOccupied(Reservation reservation);

    public void create(Reservation reservation);

    public Reservation update(Integer id, Reservation reservation);

    public void delete(Integer id);

    public List<Reservation> findByLesseeId(Integer id);

    public List<Reservation> findByFacilityId(Integer id);
}
