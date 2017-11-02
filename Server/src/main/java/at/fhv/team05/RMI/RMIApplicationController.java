package at.fhv.team05.RMI;

import at.fhv.team05.Application.ApplicationController;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;

public class RMIApplicationController extends UnicastRemoteObject implements IRMIApplicationController {
    private ApplicationController _applicationController;

    public RMIApplicationController() throws RemoteException {
        _applicationController = ApplicationController.getInstance();
    }

    @Override
    public LinkedList<BookDTO> searchForBook(String title, String author, String ISBN) throws RemoteException {
        return _applicationController.searchForBook(title, author, ISBN);
    }

    @Override
    public LinkedList<DvdDTO> searchForDvd(String title, String director, String asin) throws RemoteException {
        return _applicationController.searchForDvd(title, director, asin);
    }
}
