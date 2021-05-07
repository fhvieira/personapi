package com.diotrainning.personapi.controller.dto;

import com.diotrainning.personapi.dto.MessageReaponseDTO;
import com.diotrainning.personapi.entity.Person;
import com.diotrainning.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String foobar() {
        return "just testing";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageReaponseDTO createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }
}
