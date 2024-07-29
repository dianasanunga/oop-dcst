package com.ups.oop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {
    @GetMapping("/template")
    public String getTemplate(Model model){
        return "template";
    }

}
