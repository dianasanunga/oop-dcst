package com.ups.oop.service;


import com.ups.oop.dto.BookDTO;
import com.ups.oop.entity.Book;
import com.ups.oop.repository.BookRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDTO> getBook(){
        Iterable<Book>bookIterable = bookRepository.findAll();
        List<BookDTO> bookList = new ArrayList<>();

        for(Book bo: bookIterable){
            BookDTO book = new BookDTO();
            book.setTitle(bo.getTitle());
            book.setEditorial(bo.getEditorial());
            book.setAuthorName(bo.getAuthor().getName());
            book.setAuthorLastname(bo.getAuthor().getLastname());
            bookList.add(book);

        }
        return bookList;
    }

}
