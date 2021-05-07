package com.diotrainning.personapi.service;

import com.diotrainning.personapi.dto.MessageReaponseDTO;
import com.diotrainning.personapi.entity.Person;
import com.diotrainning.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageReaponseDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);

        return MessageReaponseDTO
                .builder()
                .message("Created person with Id " + savedPerson.getId())
                .build();
    }
}
