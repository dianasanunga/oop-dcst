package com.ups.oop.bootstrap;

import com.ups.oop.entity.*;
import com.ups.oop.entity.Loan;
import com.ups.oop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class BootStraData implements CommandLineRunner {
    private final PersonRepository personRepository;
    private final AnimalRepository animalRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;
    private final EditorialRepository editorialRepository;
    private final com.ups.oop.repository.LoanRepository loanRepository;
    private final LoanDetailRepository loanDetailRepository;


    public BootStraData(PersonRepository personRepository, AnimalRepository animalRepository, AuthorRepository authorRepository, BookRepository bookRepository, ClientRepository clientRepository, WorkerRepository workerRepository,
                        EditorialRepository editorialRepository, com.ups.oop.repository.LoanRepository loanRepository,
                        LoanDetailRepository loanDetailRepository) {
        this.personRepository = personRepository;
        this.animalRepository = animalRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.workerRepository = workerRepository;
        this.editorialRepository = editorialRepository;
        this.loanRepository = loanRepository;
        this.loanDetailRepository = loanDetailRepository;

    }


        //person
        public void createPerson() {
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
        }


        //Animals
        public void createAnimal() {
            Animal a1 = new Animal();
            a1.setPetName("Akira");
            a1.setName("Dog");
            a1.setBread("rottweiler");
            a1.setColor("black,brown");
            a1.setWeight(15.1);
            a1.setHeight(2.3);
            a1.setLength(2.2);

            Animal a2 = new Animal();
            a2.setPetName("Ares");
            a2.setName("Dog");
            a2.setBread("pastor aleman");
            a2.setColor("black");
            a2.setWeight(11.3);
            a2.setHeight(2.2);
            a2.setLength(3.2);

            Animal a3 = new Animal();
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
        }

        //Book and Authors

        public void createAuthorsAndEditorials() {
            Author au1 = new Author();
            au1.setName("Akira");
            au1.setLastname("Sanunga");
            authorRepository.save(au1);

            Book b1 = new Book();
            b1.setTitle("Conde dracula");
            b1.setEditorial("Pearson");
            b1.setAuthor(au1);
            bookRepository.save(b1);

            Book b3 = new Book();
            b3.setTitle("ratón de cuidad");
            b3.setEditorial("S.A. Editorial");
            b3.setAuthor(au1);
            bookRepository.save(b3);

            au1.getBooks().add(b1);
            au1.getBooks().add(b3);
            authorRepository.save(au1);

            Author au2 = new Author();
            au2.setName("Daniel");
            au2.setLastname("Gaibor");
            authorRepository.save(au2);

            Book b2 = new Book();
            b2.setTitle("bella");
            b2.setEditorial("LSA");
            b2.setAuthor(au2);
            bookRepository.save(b2);

            au2.getBooks().add(b2);
            authorRepository.save(au2);

            //Editorials
            Editorial e1 = new Editorial();
            e1.setName("Pearson");
            e1.getBooks().add(b1);
            e1.getBooks().add(b2);
            editorialRepository.save(e1);

            Editorial e2 = new Editorial();
            e2.setName("LNS");
            e2.getBooks().add(b2);
            e2.getBooks().add(b3);
            editorialRepository.save(e2);

            //books Join with Editorial
            b1.getEditorials().add(e1);
            bookRepository.save(b1);

            b2.getEditorials().add(e1);
            b2.getEditorials().add(e2);
            bookRepository.save(b2);

            b3.getEditorials().add(e2);
            bookRepository.save(b3);
        }

    public void createClients() {
        Client c1 = new Client("c-00001", "0925849630", "Diana", "Sanunga", 18);
        Client c2 = new Client("c-00002", "0925849622", "Miguel", "Toral", 20);
        clientRepository.save(c1);
        clientRepository.save(c2);

    }

    public void createWorkers() {
        Worker w1 = new Worker("w-00001", "0925849630", "Diana", "Sanunga", 18);
        Worker w2 = new Worker("w-00002", "0925849622", "Miguel", "Toral", 20);
        Worker w3 = new Worker("w-00003", "0925849655", "Valeria", "Adrian", 10);
        workerRepository.save(w1);
        workerRepository.save(w2);
        workerRepository.save(w3);
    }

    public void createLoans() {
        Optional<Client> clientOptional = clientRepository.findById(4l);
        Client client = new Client();
        if(clientOptional.isPresent()) {
            client = clientOptional.get();
        }

        Optional<Worker> workerOptional = workerRepository.findById(8l);
        Worker worker = new Worker();
        if(workerOptional.isPresent()) {
            worker = workerOptional.get();
        }

        Loan loan = new Loan();
        loan.setSerial("l-0001");
        loan.setLoanDate(new Date());
        loan.setClient(client);
        loan.setDays(30);
        loan.setWorker(worker);
        loanRepository.save(loan);

        Optional<Book> bookOptional = bookRepository.findById(1l);
        Book b1 = new Book();
        if(bookOptional.isPresent()) {
            b1 = bookOptional.get();
        }
        bookOptional = bookRepository.findById(2l);
        Book b2 = new Book();
        if(bookOptional.isPresent()) {
            b2 = bookOptional.get();
        }

        LoanDetail l1 = new LoanDetail();
        l1.setLoan(loan);
        l1.setBook(b1);
        loanDetailRepository.save(l1);

        LoanDetail l2 = new LoanDetail();
        l2.setLoan(loan);
        l2.setBook(b2);
        loanDetailRepository.save(l2);

        loan.getDetailList().add(l1);
        loan.getDetailList().add(l2);
        loanRepository.save(loan);
    }

    @Override
    public void run(String... args) throws Exception {
        createPerson();
        createAnimal();
        createAuthorsAndEditorials();
        createClients();
        createWorkers();
        createLoans();


        System.out.println("--------Started BootstrapData-------- ");
        System.out.println("Number of Person: " +personRepository.count());
        System.out.println("Number of Animal: " +animalRepository.count());
        System.out.println("Number of Author:" +authorRepository.count());
        System.out.println("Number of Books:" +bookRepository.count());
        System.out.println("Number of Clients:" +clientRepository.count());
        System.out.println("Number of Workers: " + workerRepository.count());
        System.out.println("Number of Loans: " + loanRepository.count());
        System.out.println("Number of Loans Details: " + loanDetailRepository.count());
    }

}
