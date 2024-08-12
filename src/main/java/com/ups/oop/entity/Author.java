package com.ups.oop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   // private String authorId;
    private String name;
    private String lastname;
    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

}
