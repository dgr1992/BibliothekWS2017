package at.fhv.team05.dtos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IBook extends Remote {

    int getID() throws RemoteException;

    String getTitle() throws RemoteException;

    String getIsbn() throws RemoteException;

    Date getReleaseDate() throws RemoteException;

    ICategory getCategory() throws RemoteException;

    String getPublisher() throws RemoteException;

    String getAuthor() throws RemoteException;

}
