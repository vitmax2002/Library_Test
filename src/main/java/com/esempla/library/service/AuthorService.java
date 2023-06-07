package com.esempla.library.service;

import com.esempla.library.model.Author;
import com.esempla.library.service.dto.AuthorDto;
import com.esempla.library.model.Book;
import com.esempla.library.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import com.esempla.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class AuthorService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public AuthorService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

    }

    public Author createAuthor(AuthorDto authorDto)
    {
        Author author=new Author();
        author.setFirstName(authorDto.firstName());
        author.setLastName(authorDto.lastName());
        for(String i:authorDto.books())
        {
            Book book=bookRepository.findById(i).orElseThrow(()->new NoSuchElementException("Nu exista carte cu indexul "+i));
            Set<Book> set= new HashSet<>();
            set.addAll(author.getBooks());
            set.add(book);
            author.setBooks(set);
        }
        return authorRepository.save(author);
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getById(int id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entitatea nu a fost găsită."));
    }

    public Author updateAuthor(int id, Author author) {
        Author author2 = authorRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        author2.setFirstName(author.getFirstName());
        author2.setLastName(author.getLastName());
        author2.setBooks(author.getBooks());
        return authorRepository.save(author2);
    }

    public String delete(int id) {
        Author author2 = authorRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        author2.setBooks(null);
        String s=author2.getFirstName();
        authorRepository.deleteById(id);
        return "Elementul cu numele "+ s+ " a fost sters cu succes";
    }
}


