package com.example.zadanie.repository;

import com.example.zadanie.model.Facility;
import com.example.zadanie.model.Reservation;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.annotation.Id;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ReservationCollectionRepository {
    private final JdbcTemplate jdbcTemplate;

    public ReservationCollectionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Reservation(rs.getInt("reservationId"),
                rs.getDate("reservationStart").toLocalDate(),
                rs.getDate("reservationEnd").toLocalDate(),
                rs.getInt("ownerId"),
                rs.getInt("lesseeId"),
                rs.getInt("reservedFacilityId"));
    }

    public List<Reservation> findAll() {
        String sql = "SELECT * FROM Reservations";
        try {
            List<Reservation> rList = jdbcTemplate.query(sql, ReservationCollectionRepository::mapRow);
            return rList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Reservation findById(Integer id) {
        String sql = "SELECT * FROM Reservations WHERE reservationId=? LIMIT 1";
        try {
            Reservation r = jdbcTemplate.queryForObject(sql, new Object[]{id}, ReservationCollectionRepository::mapRow);
            return r;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void save(Reservation reservation) {
        String sql = "INSERT INTO Reservations (reservationstart, reservationend, ownerid, " +
                "lesseeid, reservedfacilityid) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,
                reservation.reservationStart(),
                reservation.reservationEnd(),
                reservation.ownerId(),
                reservation.lesseeId(),
                reservation.reservedFacilityId());
    }

    public void update(Reservation reservation) {
        String sql = "UPDATE Reservations SET reservationstart=?, reservationend=?, ownerid=?, " +
                "lesseeid=?, reservedfacilityid=? WHERE reservationid=?";
        jdbcTemplate.update(sql,
                reservation.reservationStart(),
                reservation.reservationEnd(),
                reservation.ownerId(),
                reservation.lesseeId(),
                reservation.reservedFacilityId(),
                reservation.reservationId());
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Reservations WHERE reservationid=?";
        jdbcTemplate.update(sql, id);
    }

    public boolean checkIfFacilityOccupied(Reservation reservation) {
        boolean isCreate = false;
        if (reservation.reservationId() == null)
            isCreate = true;
        String sql = "SELECT * FROM Reservations WHERE (reservedFacilityId=? AND " +
                "(reservationId != ? OR ? = true) AND " +
                "((reservationStart>=? AND reservationStart<=?) OR " +
                "(reservationEnd>=? AND reservationEnd<=? ) OR " +
                "(reservationStart<=? AND reservationEnd>=?)))";
        List<Reservation> rList = jdbcTemplate.query(sql,
                ReservationCollectionRepository::mapRow,
                reservation.reservedFacilityId(),
                reservation.reservationId(),
                isCreate,
                reservation.reservationStart(),
                reservation.reservationEnd(),
                reservation.reservationStart(),
                reservation.reservationEnd(),
                reservation.reservationStart(),
                reservation.reservationEnd());
        if (rList.isEmpty())
            return false;
        else
            return true;
    }

    public List<Reservation> findByLesseeId(Integer id) {
        String sql = "SELECT * FROM Reservations WHERE lesseeId=? LIMIT 1";
        try {
            List<Reservation> rList = jdbcTemplate.query(sql, new Object[]{id}, ReservationCollectionRepository::mapRow);
            return rList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Reservation> findByFacilityId(Integer id) {
        String sql = "SELECT * FROM Reservations WHERE reservedFacilityId=? LIMIT 1";
        try {
            List<Reservation> rList = jdbcTemplate.query(sql, new Object[]{id}, ReservationCollectionRepository::mapRow);
            return rList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
