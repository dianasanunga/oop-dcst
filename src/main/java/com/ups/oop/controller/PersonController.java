package com.ups.oop.controller;
import com.ups.oop.dto.PersonDTO;
import com.ups.oop.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/get-all-people")
    public ResponseEntity getAllPeople() {
        return this.personService.getAllPeople();
    }

    @GetMapping("/get-person")
    public ResponseEntity getPersonById(@RequestParam String id) {
        return this.personService.getPersonById(id);
    }

    @PostMapping("/person")
    public ResponseEntity createPerson(@RequestBody PersonDTO personDTO) {
        return this.personService.createPerson(personDTO);
    }

    @PutMapping("/update-person")
    public ResponseEntity updatePerson(@RequestBody PersonDTO personDTO) {
        return this.personService.updatePerson(personDTO);
    }

    @DeleteMapping("/remove-person")
    public ResponseEntity deletePerson(@RequestParam String id) {
        return this.personService.deletePersonById(id);
    }
}
