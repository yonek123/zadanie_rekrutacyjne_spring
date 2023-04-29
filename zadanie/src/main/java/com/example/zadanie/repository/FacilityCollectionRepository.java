package com.example.zadanie.repository;

import com.example.zadanie.model.Facility;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FacilityCollectionRepository {
    private final JdbcTemplate jdbcTemplate;

    public FacilityCollectionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static Facility mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Facility(rs.getInt("facilityId"),
                rs.getString("facilityName"),
                rs.getDouble("facilityPrice"),
                rs.getDouble("facilityArea"),
                rs.getString("facilityDescription"));
    }

    public List<Facility> findAll() {
        String sql = "SELECT * FROM Facilities";
        try {
            List<Facility> fList = jdbcTemplate.query(sql, FacilityCollectionRepository::mapRow);
            return fList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Facility findById(Integer id) {
        String sql = "SELECT * FROM Facilities WHERE facilityId=? LIMIT 1";
        try {
            Facility f = jdbcTemplate.queryForObject(sql, new Object[]{id}, FacilityCollectionRepository::mapRow);
            return f;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void save(Facility facility) {
        String sql = "INSERT INTO Facilities (facilityname, facilityprice, facilityarea, " +
                "facilitydescription) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql,
                facility.facilityName(),
                facility.facilityPrice(),
                facility.facilityArea(),
                facility.facilityDescription());
    }

    public void update(Facility facility) {
        String sql = "UPDATE Facilities SET facilityname=?, facilityprice=?, facilityarea=?, " +
                "facilitydescription=? WHERE facilityId=?";
        jdbcTemplate.update(sql,
                facility.facilityName(),
                facility.facilityPrice(),
                facility.facilityArea(),
                facility.facilityDescription(),
                facility.facilityId());
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Facilities WHERE facilityId=?";
        jdbcTemplate.update(sql, id);
    }

    public Facility findByName(String name) {
        String sql = "SELECT * FROM Facilities WHERE facilityName=? LIMIT 1";
        try {
            Facility f = jdbcTemplate.queryForObject(sql, new Object[]{name}, FacilityCollectionRepository::mapRow);
            return f;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
