package at.fhv.team05.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import at.fhv.team05.dtos.IBook;
import at.fhv.team05.persistence.DBFacade;

@Entity
@Table(name = "Book")
public class Book implements Serializable, IBook {
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

    public Book(){}

    public int getID(){
        return _id;
    }

    public void setID(int id){
        _id = id;
    }

    public String getTitle(){
        return _title;
    }

    public void setTitle(String title){
        _title = title;
    }

    public String getIsbn(){
        return _isbn;
    }

    public void setIsbn(String isbn){
        _isbn = isbn;
    }

    public Date getReleaseDate(){
        return _releaseDate;
    }

    public void setReleaseDate(Date releaseDate){
        _releaseDate = releaseDate;
    }

    public Category getCategory(){
        return _category;
    }

    public void setCategory(Category category){
        _category = category;
    }

    public String getPublisher(){
        return _publisher;
    }

    public void setPublisher(String publisher){
        _publisher = publisher;
    }

    public String getAuthor(){
        return _author;
    }

    public void setAuthor(String author){
        _author = author;
    }

    public static void main( String[] args ){
        DBFacade dbFacade = DBFacade.getInstance();

        List<Book> books = dbFacade.getAllBooks();

        Book book = dbFacade.getBook("Der kleine Hobbit");
        System.out.println(book);
    }

    @Override
    public String toString(){
        return "Title: " + _title + ", ISBN: " + _isbn + ", ReleaseDate: " + _releaseDate + ", Category: " + _category.getCategoryName() + ", Publisher: " + _publisher + ", Author: " + _author;
    }
}
