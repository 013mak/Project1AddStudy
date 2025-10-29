package com.mak.spring.models;


import jakarta.validation.constraints.*;


public class Person {

    private int personId;

    @NotNull(message = "Must be not null")
    @Size(min = 2, max = 20, message = "2-20")
    private String name;
    @Positive(message = "More than 0")
    private int birthYear;


    public Person() {
    }

    public Person(int personId, String name, int birthYear) {
        this.personId = personId;
        this.name = name;
        this.birthYear = birthYear;
    }



    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int id) {
        this.personId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }


}
