package com.diotrainning.personapi.service;

import com.diotrainning.personapi.dto.MessageResponseDTO;
import com.diotrainning.personapi.dto.PersonDTO;
import com.diotrainning.personapi.entity.Person;
import com.diotrainning.personapi.exception.PersonNotFoundException;
import com.diotrainning.personapi.mapper.PersonMapper;
import com.diotrainning.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person person = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(person);

        return createMessageResponse(savedPerson.getId(), "Created person with Id ");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();

        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = getPersonOrThrow(id);

        return personMapper.toDTO(person);
    }

    public void delete(long id) throws PersonNotFoundException {
        getPersonOrThrow(id);

        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        getPersonOrThrow(id);

        Person person = personMapper.toModel(personDTO);

        personRepository.save(person);

        return createMessageResponse(id, "Update person with Id ");
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    private Person getPersonOrThrow(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
}
