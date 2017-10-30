package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.dtos.IBook;

import java.rmi.Remote;

public interface SearchForBook<T> extends Remote {
    IBook searchForBook(String title, String author, String ISBN, String Genre);
}
