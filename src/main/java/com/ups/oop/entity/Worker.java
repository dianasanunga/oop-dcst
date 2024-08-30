package com.ups.oop.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Worker extends Person {
    private String workerCode;
    @OneToMany(mappedBy = "worker")
    private List<Loan> loans = new ArrayList<>();

    public Worker() {
        super();
    }

    public Worker(String workerCode, String personId, String name, String lastname, Integer age) {
        super(personId, name, lastname, age);
        this.workerCode = workerCode;
    }

}





