package library.service;

import library.model.Author;
import library.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;

    }

    public Author createAuthor(Author author)
    {
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
        author2.setFirst_name(author.getFirst_name());
        author2.setLast_name(author.getLast_name());
        author2.setBooks(author.getBooks());
        return authorRepository.save(author2);
    }

    public String delete(int id) {
        Author author2 = authorRepository.findById(id).orElseThrow(()->new NoSuchElementException("nu exista element cu asa id"));
        String s=author2.getFirst_name();
        authorRepository.deleteById(id);
        return "Elementul cu numele "+ s+ " a fost sters cu succes";
    }
}


