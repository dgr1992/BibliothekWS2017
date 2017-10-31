package at.fhv.team05.dtos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface IDvd extends Remote {

    int getId() throws RemoteException;

    String getTitle() throws RemoteException;

    String getAsin() throws RemoteException;

    Date getReleaseDate() throws RemoteException;

 //   ICategory getCategory() throws RemoteException;

    String getPublisher() throws RemoteException;

   // String getDirector() throws RemoteException;

}
