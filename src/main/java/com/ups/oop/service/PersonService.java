package com.ups.oop.service;


import com.ups.oop.dto.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<Person> personList = new ArrayList<>();

    public ResponseEntity createPerson(Person person){
        boolean wasFound = findPerson(person.getId());
        if(wasFound){
            String errorMessage = "person with id" + person.getId() + "already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }else{
            personList.add(person);
            return ResponseEntity.status(HttpStatus.OK).body(person);
        }
    }
    private boolean findPerson(String id){
        for(Person person: personList){
            if(id.equalsIgnoreCase(person.getId())){
                return true;
            }
        }
        return false;
    }

    public ResponseEntity getAllPeople(){
        if(personList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    public ResponseEntity getPersonById(String id){
        Person person= new Person();
        for(Person per: personList) {
            if(id.equalsIgnoreCase(per.getId())){
                return ResponseEntity.status(HttpStatus.OK).body(per);
            }
        }

        String errorMessage = "person with id" + id +  " already exists";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    public Person updatePerson(Person person){
        Person per = new Person();
        int index = 0;
        for(Person pers : personList){
            if(person.getId().equalsIgnoreCase(pers.getId())){
                personList.set(index,person);
                return person;
            }
            index++;
        }
        return per;
    }

    public String deletePersonById(String id){
        String message = "person with id " + id;
        for(Person per :personList){
            if(id.equalsIgnoreCase(per.getId())){
                personList.remove(per);
                return message + "removed successfully";
            }
        }
        return message + "not found";
    }
}