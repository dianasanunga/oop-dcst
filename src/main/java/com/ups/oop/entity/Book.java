package com.ups.oop.entity;

import jakarta.persistence.*;
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

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String editorial;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true )
    private Author author;
    @ManyToMany
    @JoinTable(
            name="book_editorial",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "editorial_id")
    )
    private List<Editorial> editorials = new ArrayList<>();
    @OneToMany(mappedBy = "book")
    private List<LoanDetail> loanDetails = new ArrayList<>();

}
