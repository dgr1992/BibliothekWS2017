package at.fhv.team05.dtos;

import at.fhv.team05.dtos.IBook;
import at.fhv.team05.dtos.ICategory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class BookDTO extends UnicastRemoteObject implements IBook {

    private int id;
    private String title;
    private String isbn;
    private Date releaseDate;
    private ICategory categoryId;
    private String publisher;
    private String author;

    public BookDTO(IBook book) throws RemoteException {

        id = book.getId();
        title = book.getTitle();
        isbn = book.getIsbn();
        releaseDate = book.getReleaseDate();
        categoryId = book.getCategory();
        publisher = book.getPublisher();
        author = book.getAuthor();

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public Date getReleaseDate() {
        return releaseDate;
    }

    @Override
    public ICategory getCategory() throws RemoteException {
        return null;
    }

    @Override
    public String getPublisher() {
        return publisher;
    }

    @Override
    public String getAuthor() {
        return author;
    }
}
