package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.dtos.IBook;

import java.rmi.Remote;
import java.util.LinkedList;

public interface SearchForBook<T> extends Remote {
    LinkedList<IBook> searchForBook(String title, String author, String ISBN);
}
