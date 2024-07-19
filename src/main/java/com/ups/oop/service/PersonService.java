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

    public ResponseEntity createPerson(PersonDTO personDTO){
        boolean wasFound = findPerson(personDTO.getId());
        if(wasFound){
            String errorMessage = "person with id " + personDTO.getId() + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }else{
            personDTOList.add(personDTO);
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        }
    }
    private boolean findPerson(String id){
        for(PersonDTO personDTO : personDTOList){
            if(id.equalsIgnoreCase(personDTO.getId())){
                return true;
            }
        }
        return false;
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

    private int findIndexById (String id){
        int index = 0;
        for (PersonDTO p : personDTOList){
            if(id.equalsIgnoreCase(p.getId())){
               return index;
            }
            index++;
        }
        return -1;

    }

    public ResponseEntity updatePerson(PersonDTO personDTO){
     int upadateIndex = findIndexById(personDTO.getId());
        if (upadateIndex!= -1){
            personDTOList.set(upadateIndex, personDTO);
            return ResponseEntity.status(HttpStatus.OK).body(personDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("person with id " + personDTO.getId()+" doesn't exits ");

    }

    public ResponseEntity deletePersonById(String id){
        String message = "person with id " + id;
        for(PersonDTO per : personDTOList){
            if(id.equalsIgnoreCase(per.getId())){
                personDTOList.remove(per);
                return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
    }
}