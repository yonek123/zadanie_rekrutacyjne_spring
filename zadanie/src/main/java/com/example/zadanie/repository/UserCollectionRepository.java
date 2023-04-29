package com.example.zadanie.repository;

import com.example.zadanie.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserCollectionRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserCollectionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt("userId"),
                rs.getString("userName"));
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM Users";
        try {
            List<User> uList = jdbcTemplate.query(sql, UserCollectionRepository::mapRow);
            return uList;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User findById(Integer id) {
        String sql = "SELECT * FROM Users WHERE userId=? LIMIT 1";
        try {
            User u = jdbcTemplate.queryForObject(sql, new Object[]{id}, UserCollectionRepository::mapRow);
            return u;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void save(User user) {
        String sql = "INSERT INTO Users (userName) VALUES (?)";
        jdbcTemplate.update(sql, user.userName());
    }

    public void update(User user) {
        String sql = "UPDATE Users SET userName=? WHERE userId=?";
        jdbcTemplate.update(sql, user.userName(), user.userId());
    }

    public void delete(Integer id) {
        String sql = "DELETE FROM Users WHERE userId=?";
        jdbcTemplate.update(sql, id);
    }

    public User findByName(String name) {
        String sql = "SELECT * FROM Users WHERE userName=? LIMIT 1";
        try {
            User u = jdbcTemplate.queryForObject(sql, new Object[]{name}, UserCollectionRepository::mapRow);
            return u;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
