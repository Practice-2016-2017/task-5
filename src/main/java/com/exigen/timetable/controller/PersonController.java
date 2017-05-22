package com.exigen.timetable.controller;

import com.exigen.timetable.pojo.Person;
import com.exigen.timetable.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private
    PersonRepository personRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addPerson(@RequestBody Person input) {
        Person result = personRepository.save(input);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/{personId}", method = RequestMethod.GET)
    public Person getPerson(@PathVariable Long personId) {
        return personRepository.findOne(personId);
    }
}
