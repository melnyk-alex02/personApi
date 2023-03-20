package com.alex.personApi.controller;

import com.alex.personApi.dto.PersonDTO;
import com.alex.personApi.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/person/{id}")
    public PersonDTO getPerson(@PathVariable Long id){
        return personService.getPerson(id);
    }
}
