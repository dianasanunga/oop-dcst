package com.ups.oop.controller;


import com.ups.oop.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private  final PersonService personService;
    private final AnimalService animalService;
    private final BookService bookService;
    private final ClientService clientService;
    private final WorkerService workerService;


    public TemplateController(PersonService personService, AnimalService animalService, BookService bookService, ClientService clientService, WorkerService workerService) {
        this.personService = personService;
        this.animalService = animalService;
        this.bookService = bookService;
        this.clientService = clientService;
        this.workerService = workerService;
    }

    @GetMapping("/template")
    public String getTemplate(Model model){
        return "template";
    }

    @GetMapping("/people")
    public String getPeople(Model model){
        model.addAttribute("people", personService.getPeople());
        return "person/list";

    }

    @GetMapping("/animal")
    public String getAnimal(Model model) {
        model.addAttribute("animal", animalService.getAnimal());
        return "animal/list";
    }

    @GetMapping("/book")
    public String getBook(Model model) {
        model.addAttribute("book", bookService.getBook());
        return "book/list";
    }

    @GetMapping("/clients")
    public String getClients(Model model) {
        model.addAttribute("clients", clientService.getClients());
        return "client/list";
    }

//    @GetMapping("/workers")
//    public String getWorkers(Model model) {
//        model.addAttribute("workers", workerService.getWorkers());
//        return "worker/list";
//    }


}
