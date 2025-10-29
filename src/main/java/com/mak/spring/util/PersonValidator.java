package com.mak.spring.util;

import com.mak.spring.dao.PersonDAO;
import com.mak.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
//
//        if (personDAO.show(person.getBirthYear()).isPresent()) {
//            errors.rejectValue("email", null, "Email is already in use");
//        }
//        if (personDAO.show(person.getEmail()).isPresent()) {
//            errors.rejectValue("email", null, "Email is already in use");
//        }
    }
}
