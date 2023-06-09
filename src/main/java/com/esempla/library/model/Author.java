package com.esempla.library.model;


import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", length=45)
    private String firstName;


    @Column(name = "last_name", length=45)
    private String lastName;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "book_author",joinColumns = @JoinColumn(name = "author_id",referencedColumnName = "id"),
                                    inverseJoinColumns = @JoinColumn(name="book_isbn",referencedColumnName = "isbn"))
    private Set<Book> books=new HashSet<>();

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Author(String firstName, String lastName, Set<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return "firstName: "+firstName+" lastName: "+lastName;
    }

    @Override
    public int hashCode() {
        return  id * firstName.hashCode() * lastName.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if(this==object) return true;
        if(!(object instanceof Author)) return false;
        Author author=(Author) object;
        return this.id==author.id && this.firstName.equals(author.firstName) && this.lastName.equals(author.lastName);
    }
}