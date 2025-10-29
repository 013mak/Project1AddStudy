package com.mak.spring.dao;

import com.mak.spring.models.Book;
import com.mak.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Book> hasBooks(int personId) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new BeanPropertyRowMapper<>(Book.class), personId);
    }


    public List<Book> index() {

        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public Optional<Book> show(String email) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE email = ?", new Object[]{email}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES( ?, ?, ?) ", book.getTitle(), book.getAuthor(), book.getYear());

    }
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?", updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);

    }
    public void submit (int id, int personId) {
        jdbcTemplate.update(
                "UPDATE Book SET person_id = ? WHERE id = ?",
                personId,
                id
        );
    }
    public void free (int id) {
        jdbcTemplate.update(
                "UPDATE Book SET person_id = null WHERE id = ?",
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }


}
