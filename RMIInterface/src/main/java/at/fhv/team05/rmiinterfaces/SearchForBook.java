package at.fhv.team05.rmiinterfaces;


import java.rmi.Remote;

public interface SearchForBook<T> extends Remote {
    T searchForBook(String title, String author, String ISBN, String Genre);
}
