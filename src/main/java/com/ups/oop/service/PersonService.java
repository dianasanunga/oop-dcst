package com.ups.oop.service;


import com.ups.oop.dto.Person;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    private List<Person> personList = new ArrayList<>();

    public Person createPerson(Person person){
        personList.add(person);
        return person;

    }
    public List<Person> getAllPeople(){
        return personList;
    }

    public Person getPersonById(String id){
        Person person= new Person();
        for(Person per: personList) {
            if(id.equalsIgnoreCase(per.getId())){
                return per;
            }
        }
        return person;
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