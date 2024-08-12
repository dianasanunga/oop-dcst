package com.ups.oop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class BookDTO {

    private String title;
    private String editorial;
    private String authorName;
    private String authorLastname;

}
