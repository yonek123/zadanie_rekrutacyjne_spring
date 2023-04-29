package com.example.zadanie.controller;

import com.example.zadanie.model.Facility;
import com.example.zadanie.model.Reservation;
import com.example.zadanie.model.User;
import com.example.zadanie.service.FacilityService;
import com.example.zadanie.service.ReservationService;
import com.example.zadanie.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    UserService userService;
    @Autowired
    FacilityService facilityService;

    @GetMapping("")
    public List<Reservation> findAll() {
        return reservationService.findAll();
    }

    @GetMapping("/{id}")
    public Reservation findById(@PathVariable Integer id) {
        Reservation r = reservationService.findById(id);
        if (r == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found!");
        else
            return r;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Reservation reservation) {
        if (reservationService.checkIfFacilityOccupied(reservation))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Facility occupied!");
        else
            reservationService.create(reservation);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @Valid @RequestBody Reservation reservation) {
        if (id != reservation.reservationId())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad request!");
        else if (reservationService.checkIfFacilityOccupied(reservation))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Facility occupied!");
        else if (reservationService.update(id, reservation) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found!");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        if (reservationService.findById(id) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found!");
        else
            reservationService.delete(id);
    }

    @GetMapping("/lessee/{name}")
    public List<Reservation> findByLesseeName(@PathVariable String name) {
        User u = userService.findByName(name);
        if (u == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found!");
        else {
            return reservationService.findByLesseeId(u.userId());
        }
    }

    @GetMapping("/facility/{name}")
    public List<Reservation> findByFacilityName(@PathVariable String name) {
        Facility f = facilityService.findByName(name);
        if (f == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found!");
        else {
            return reservationService.findByFacilityId(f.facilityId());
        }
    }
}
