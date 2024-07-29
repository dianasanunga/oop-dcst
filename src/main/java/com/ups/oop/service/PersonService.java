package com.ups.oop.service;

import com.ups.oop.dto.PersonDTO;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class  PersonService {
    private final PersonRepository personRepository;

    private List<PersonDTO> personDTOList = new ArrayList<>();

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ResponseEntity createPerson(PersonDTO personDTO) {
        String personId = personDTO.getId();
        //check repository if record exist
        Optional<Person> personOptional = personRepository.findByPersonId(personId);
        if (personOptional.isPresent()) {
            String errorMessage = "person with id " + personDTO.getId() + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before register Person, name, Lastname are present
            if (personDTO.getName().contains(" ")) {
                //Build Person and save in Repository
                Person personRecord = new Person();
                personRecord.setPersonId(personId);
                String[] nameStrings = personDTO.getName().split(" ");
                String name = nameStrings[0];
                String lastname = nameStrings[1];
                personRecord.setName(name);
                personRecord.setLastname(lastname);
                personRecord.setAge(personDTO.getAge());
                personRepository.save(personRecord);
                return ResponseEntity.status(HttpStatus.OK).body(personDTO);

            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("person name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllPeople(){
        Iterable<Person>personIterable = personRepository.findAll();
        List<PersonDTO> peopleList = new ArrayList<>();

        for(Person p: personIterable){
           PersonDTO person = new PersonDTO(p.getPersonId(), p.getName()+ "-" + p.getLastname(), p.getAge());
           peopleList.add(person);

//            PersonDTO person = new PersonDTO();
//            person.setId(p.getPersonId());
//            person.setName(p.getName() + "-" + p.getLastname());
//            person.setAge(p.getAge());
//            peopleList.add(person);
        }

        if(peopleList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(peopleList);
    }

    public ResponseEntity getPersonById(String personId){

       //Optional<Person> personOptional = personRepository.findById(Long.valueOf(id));
        Optional<Person> personOptional = personRepository.findByPersonId(personId);

       if(personOptional.isPresent()){
           //if record was found
           Person personFound = personOptional.get();
           PersonDTO person = new PersonDTO(personFound.getPersonId(),
                     personFound.getName() + "-" + personFound.getLastname(),
                     personFound.getAge());
           return ResponseEntity.status(HttpStatus.OK).body(person);
       } else {
           //if record wasn't found
           String errorMessage = "Person with id " + personId + " not found";
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

       }
    }

    public ResponseEntity updatePerson(PersonDTO personDTO){
        String requestId = personDTO.getId();
        //check repository if record exits
        Optional<Person> personOptional = personRepository.findByPersonId(requestId);
        if(personOptional.isPresent()){
            Person personRecord = personOptional.get();
            if (personDTO.getName().contains(" ")) {
            personRecord.setPersonId(requestId);
            String[] nameStrings = personDTO.getName().split(" ");
            String name = nameStrings[0];
            String lastname = nameStrings[1];
            personRecord.setName(name);
            personRecord.setLastname(lastname);
            personRecord.setAge(personDTO.getAge());
            personRepository.save(personRecord);
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("person name must contain two strings separated by a whitespace");
        }

        }else {
            String errorMessage = "person with id " + requestId + "not fund";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deletePersonById(String id){
        //personRepository. delete();
        String message = "person with id " + id;
        Optional<Person> personOptional = personRepository.findByPersonId(id);
        if(personOptional.isPresent()){
            personRepository.delete(personOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}