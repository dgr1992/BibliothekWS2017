package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.dtos.IBook;
import at.fhv.team05.dtos.IDvd;

import java.rmi.RemoteException;
import java.util.LinkedList;

public interface IRMIApplicationController {
    LinkedList<IBook> searchForBook(String title, String author, String ISBN) throws RemoteException;

    LinkedList<IDvd> searchForDvd(String title, String director, String asin) throws RemoteException;
}
