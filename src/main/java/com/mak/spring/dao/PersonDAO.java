package com.mak.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.mak.spring.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }






    public List<Person> index() {

        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int personId) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id = ?", new Object[]{personId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }
//    public Optional<Person> show(String email) {
//        return jdbcTemplate.query("SELECT * FROM Person WHERE email = ?", new Object[]{email}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny();
//    }

    public void save(Person person) {
            jdbcTemplate.update("INSERT INTO Person(name, birth_year) VALUES(?, ?) ", person.getName(), person.getBirthYear());

    }
    public void update(int personId, Person updatedPerson) {
            jdbcTemplate.update("UPDATE Person SET name=?, birth_year=?, WHERE person_id=?", updatedPerson.getName(), updatedPerson.getBirthYear(), personId);
    }

    public void delete(int personId) {
            jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", personId);
    }

    public Person hasBook(int personId) {
        return jdbcTemplate.query("SELECT * FROM Person JOIN Book ON Person.person_id = Book.person.id WHERE person_id = ?", new Object[]{personId}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);

    }


}
