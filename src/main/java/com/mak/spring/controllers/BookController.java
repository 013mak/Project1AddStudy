package com.mak.spring.controllers;

import com.mak.spring.dao.BookDAO;
import com.mak.spring.dao.PersonDAO;
import com.mak.spring.models.Book;
import com.mak.spring.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("book", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        if (bookDAO.show(id).getPersonId() != null) model.addAttribute("person1", personDAO.show(bookDAO.show(id).getPersonId()));
        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") Book book) {

        bookDAO.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") Book book,  @PathVariable("id") int id) {
        bookDAO.update(id, book);
        return "redirect:/book";
    }
    @PatchMapping("/{id}/submit")
    public String submit(@ModelAttribute("book") Book book, @ModelAttribute("person") Person person) {
       bookDAO.submit(book.getId(), person.getPersonId());
        return "redirect:/book";
    }


    @PatchMapping("/{id}/free")
    public String free(@ModelAttribute("book") Book book) {
        bookDAO.free(book.getId());

        return "redirect:/book";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }
}
