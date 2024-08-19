package com.ups.oop.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Client extends Person{
    private String clientCode;

    public Client(){
        super();
    }

    public Client(String clientCode, String personId, String name, String lastname, Integer age ){
        super(personId, name, lastname, age);
        this.clientCode = clientCode;
    }

}
