package com.example.zadanie.service;

import com.example.zadanie.model.Reservation;
import com.example.zadanie.repository.ReservationCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationCollectionRepository repository;

    public List<Reservation> findAll() {
        return repository.findAll();
    }

    public Reservation findById(Integer id) {
        Reservation r = repository.findById(id);
        return r;
    }

    public boolean checkIfFacilityOccupied(Reservation reservation) {
        return repository.checkIfFacilityOccupied(reservation);
    }

    public void create(Reservation reservation) {
        repository.save(reservation);
    }

    public Reservation update(Integer id, Reservation reservation) {
        if (repository.findById(id) == null)
            return null;
        else {
            repository.update(reservation);
            return reservation;
        }
    }

    public void delete(Integer id) {
        repository.delete(id);
    }

    public List<Reservation> findByLesseeId(Integer id) {
        List<Reservation> r = repository.findByLesseeId(id);
        if (r == null)
            return null;
        else
            return r;
    }

    public List<Reservation> findByFacilityId(Integer id) {
        List<Reservation> r = repository.findByFacilityId(id);
        if (r == null)
            return null;
        else
            return r;
    }
}
