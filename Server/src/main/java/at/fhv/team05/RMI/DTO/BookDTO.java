package at.fhv.team05.RMI.DTO;

import at.fhv.team05.domain.Book;
import at.fhv.team05.dtos.IBook;
import at.fhv.team05.dtos.ICategory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class BookDTO extends UnicastRemoteObject implements IBook {
    private Book _book;

    public BookDTO(Book book) throws RemoteException {
        _book = book;
    }

    @Override
    public int getId() {
        return _book.getId();
    }

    @Override
    public String getTitle() {
        return _book.getTitle();
    }

    @Override
    public String getIsbn() {
        return _book.getIsbn();
    }

    @Override
    public Date getReleaseDate() {
        return _book.getReleaseDate();
    }

    @Override
    public ICategory getCategory() {
        return _book.getCategory();
    }

    @Override
    public String getPublisher() {
        return _book.getPublisher();
    }

    @Override
    public String getAuthor() {
        return _book.getAuthor();
    }
}
