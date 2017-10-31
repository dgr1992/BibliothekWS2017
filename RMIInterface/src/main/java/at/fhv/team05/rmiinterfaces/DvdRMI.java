package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.dtos.IDvd;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;

public interface DvdRMI extends Remote {
    LinkedList<IDvd> searchForDvd(String title, String director, String asin) throws RemoteException;
}
