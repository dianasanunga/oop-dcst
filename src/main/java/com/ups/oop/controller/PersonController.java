package com.ups.oop.controller;

import com.ups.oop.dto.Person;
import com.ups.oop.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController( PersonService personService){
        this.personService = personService;
    }


    @GetMapping("/get-all-people")
    public ResponseEntity getAllPeople(){
        return this.personService.getAllPeople();
    }

    @GetMapping("/get-person")
    public ResponseEntity getPersonById(@RequestParam String id) {
        return this.personService.getPersonById(id);
    }

    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody Person person){
        return  this.personService.createPerson(person);
    }

    @PutMapping("/update-person")
    public ResponseEntity updatePerson(@RequestBody Person person){
        return this.personService.updatePerson(person);

    }

    @DeleteMapping("/removed-person")
    public ResponseEntity deletePerson(@RequestParam String id){
        return this.personService.deletePersonById(id);
    }
}
