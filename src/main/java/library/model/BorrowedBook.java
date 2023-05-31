package library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrowed_book")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "book_isbn")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;


    public BorrowedBook(Book book, Client client, LocalDate borrowDate) {
        //this.book = book;
        this.client = client;
        this.borrowDate = borrowDate;
    }

    public BorrowedBook() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
      return book;
   }

    public void setBook(Book book) {
        this.book = book;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }
}