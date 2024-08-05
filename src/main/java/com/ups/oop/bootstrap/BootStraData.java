package com.ups.oop.bootstrap;

import com.ups.oop.entity.Animal;
import com.ups.oop.entity.Person;
import com.ups.oop.repository.AnimalRepository;
import com.ups.oop.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStraData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;

    public BootStraData(PersonRepository personRepository, AnimalRepository animalRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //person

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

        Person p3 = new Person();
        p3.setPersonId("0923568974");
        p3.setName("Miguel");
        p3.setLastname("Adrian");
        p3.setAge(20);

        personRepository.save(p1);
        personRepository.save(p2);
        personRepository.save(p3);


        //Animals

        Animal a1 = new Animal();
        a1.setPetName("Akira");
        a1.setName("Dog");
        a1.setBread("rottweiler");
        a1.setColor("black,brown");
        a1.setWeight(15.1);
        a1.setHeight(2.3);
        a1.setLength(2.2);

        Animal a2= new Animal();
        a2.setPetName("Ares");
        a2.setName("Dog");
        a2.setBread("pastor aleman");
        a2.setColor("black");
        a2.setWeight(11.3);
        a2.setHeight(2.2);
        a2.setLength(3.2);

        Animal a3= new Animal();
        a3.setPetName("Odie");
        a3.setName("Dog");
        a3.setBread("beagle");
        a3.setColor("White,coffee");
        a3.setWeight(15.1);
        a3.setHeight(3.2);
        a3.setLength(7.2);

        animalRepository.save(a1);
        animalRepository.save(a2);
        animalRepository.save(a3);

        System.out.println("--------Started BootstrapData-------- ");
        System.out.println("Number of Person: " +personRepository.count());
        System.out.println("Number of animal: " +animalRepository.count());
    }
}
