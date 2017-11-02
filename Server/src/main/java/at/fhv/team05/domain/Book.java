package at.fhv.team05.domain;

import at.fhv.team05.dtos.IBook;
import at.fhv.team05.persistence.DBFacade;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book implements IBook {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int _id;

    @Column(name = "title")
    private String _title;

    @Column(name = "isbn")
    private String _isbn;

    @Column(name = "releaseDate")
    private Date _releaseDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId")
    private Category _category;
/*
    @Column(name = "categoryId")
    private int _category;*/

    @Column(name = "publisher")
    private String _publisher;

    @Column(name = "author")
    private String _author;

    public Book() {
    }

    @Override
    public int getId() {
        return _id;
    }

    public void setID(int id) {
        _id = id;
    }

    @Override
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    @Override
    public String getIsbn() {
        return _isbn;
    }

    public void setIsbn(String isbn) {
        _isbn = isbn;
    }

    @Override
    public Date getReleaseDate() {
        return _releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        _releaseDate = releaseDate;
    }

    @Override
    public Category getCategory() {
        return _category;
    }

    public void setCategory(Category category) {
        _category = category;
    }

    @Override
    public String getPublisher() {
        return _publisher;
    }

    public void setPublisher(String publisher) {
        _publisher = publisher;
    }

    @Override
    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        _author = author;
    }

    public static void main(String[] args) {
        DBFacade dbFacade = DBFacade.getInstance();

        List<Book> books = dbFacade.getAllBooks();

        Book book = dbFacade.getBook("Der kleine Hobbit");
        System.out.println(book);
    }

    @Override
    public String toString() {
        return "Title: " + _title + ", ISBN: " + _isbn + ", ReleaseDate: " + _releaseDate + ", Category: " + _category.getCategoryName() + ", Publisher: " + _publisher + ", Author: " + _author;
    }
}
