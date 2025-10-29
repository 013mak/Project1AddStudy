package com.mak.spring.controllers;

import com.mak.spring.dao.BookDAO;
import com.mak.spring.models.Book;
import com.mak.spring.models.Person;
import com.mak.spring.util.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.mak.spring.dao.PersonDAO;

import java.util.List;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/people")
public class PeopleController {


    private final PersonDAO personDAO;
    private final PersonValidator personValidator;
    private final BookDAO bookDAO;


    public PeopleController(PersonDAO personDAO, PersonValidator personValidator, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
        this.bookDAO = bookDAO;

    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{personId}")
    public String show(@PathVariable("personId") int personId, Model model) {
        List<Book> hasbook = bookDAO.hasBooks(personId);
        model.addAttribute("person", personDAO.show(personId));
        //model.addAttribute("books", bookDAO.show(personId));
        if (!(hasbook.isEmpty())) model.addAttribute("books", bookDAO.hasBooks(personId));


        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) return "people/new";
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{personId}/edit")
    public String edit(@PathVariable("personId") int personId, Model model) {
        model.addAttribute("person", personDAO.show(personId));
        return "people/edit";
    }

    @PatchMapping("/{personId}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("personId") int personId) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) return "people/edit";
        personDAO.update(personId, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{personId}")
    public String delete(@PathVariable("personId") int personId) {
        personDAO.delete(personId);
        return "redirect:/people";
    }

//    @GetMapping("/{personId}")
//    public String hasBook(@PathVariable("personId") int personId, Model model) {
//        model.addAttribute("person", personDAO.show(personId));
//        return "people/showwithbook";
//    }
}
