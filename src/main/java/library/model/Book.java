package library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="book")
public class Book {
    @Id
    @Column(length = 13, unique = true)
    private String isbn;
    @Column(name = "name", length=100)
    private String name;
    @ManyToOne
    @JoinColumn(name="publisher_id",referencedColumnName = "id")
    private Publisher publisher;
    @Column(name="publisher_year",length =4 )
    private String publishYear;
    @Column(name="copies")
    private int copies;
    @Column(name="picture")
    private String picture;

    @JsonIgnore
    @OneToMany(mappedBy = "book",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<BorrowedBook> borrowedBooks= new HashSet<>();


    public Set<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(Set<BorrowedBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @ManyToMany(mappedBy = "books",fetch = FetchType.LAZY)
   private Set<Author> authors;
    public Book(String isbn,
                String name,
                Publisher publisher,
                String publishYear,
                int copies,
                String picture) {
        this.isbn = isbn;
        this.name = name;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.copies = copies;
        this.picture = picture;
    }

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
