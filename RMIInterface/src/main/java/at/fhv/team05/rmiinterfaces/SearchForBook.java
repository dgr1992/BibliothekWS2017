package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.domain.Book;

import java.rmi.Remote;

public interface SearchForBook extends Remote {
    Book searchForBook(String title, String author, String ISBN, String Genre);
}
