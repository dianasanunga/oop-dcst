package com.ups.oop.controller;


import com.ups.oop.service.AnimalService;
import com.ups.oop.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    private  final PersonService personService;
    private final AnimalService animalService;

    public TemplateController(PersonService personService, AnimalService animalService) {
        this.personService = personService;
        this.animalService = animalService;
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

}
