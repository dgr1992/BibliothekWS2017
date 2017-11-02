package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.RentalDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface IRMIApplicationController extends Remote {
    LinkedList<BookDTO> searchForBook(String title, String author, String ISBN) throws RemoteException;

    LinkedList<DvdDTO> searchForDvd(String title, String director, String asin) throws RemoteException;

    void rentMedium(RentalDTO rental) throws RemoteException;
}
