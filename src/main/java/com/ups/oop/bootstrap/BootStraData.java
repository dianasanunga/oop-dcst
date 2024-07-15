package com.ups.oop.bootstrap;

import com.ups.oop.entity.Person;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStraData implements CommandLineRunner {
    private final PersonRepository personRepository;

    public BootStraData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Person p1 = new Person();
        p1.setPersonId("0920005311");
        p1.setName("Diana");
        p1.setLastname("Sanunga");
        p1.setAge(18);

        Person p2 = new Person();
        p2.setPersonId("0992162026");
        p2.setName("Valeska");
        p2.setLastname("Adrian");
        p2.setAge(5);

        personRepository.save(p1);
        personRepository.save(p2);

        System.out.println("--------Started BootstrapData-------- ");
        System.out.println("Number of Person: " +personRepository.count());
    }
}
