package com.ups.oop.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class BookDTO {

    private String title;
    private String author;
    private List<String> editorials;

}
