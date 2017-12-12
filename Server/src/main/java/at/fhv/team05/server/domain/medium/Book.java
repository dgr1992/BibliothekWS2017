package at.fhv.team05.server.domain.medium;

import at.fhv.team05.library.ObjectInterfaces.*;
import at.fhv.team05.server.domain.IDomainObject;
import at.fhv.team05.server.persistence.Repository;
import at.fhv.team05.server.persistence.RepositoryFactory;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "Book")
public class Book extends Medium implements IBook, IDomainObject {
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

    @Override
    @Transient
    public Map<String, Object> getAttributeMap() {
        return null;
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
        Repository<Book> rep = RepositoryFactory.getRepositoryInstance(Book.class);


        List<Book> books = rep.list();

        Book book = rep.list().get(0);
        System.out.println(book);
    }

    @Override
    public String toString() {
        return "Title: " + _title + ", ISBN: " + _isbn + ", ReleaseDate: " + _releaseDate + ", Publisher: " + _publisher + ", Author: " + _author;
    }
}
