package at.fhv.team05.RMI;

import at.fhv.team05.Application.ApplicationController;
import at.fhv.team05.dtos.*;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class RMIApplicationController extends UnicastRemoteObject implements IRMIApplicationController {
    private ApplicationController _applicationController;

    public RMIApplicationController() throws RemoteException {
        _applicationController = ApplicationController.getInstance();
    }

    @Override
    public LinkedList<BookDTO> searchForBook(BookDTO book) throws RemoteException {
        return _applicationController.searchForBook(book);
    }

    @Override
    public LinkedList<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException {
        return _applicationController.searchForDvd(dvd);
    }

    @Override
    public void rentMedium(RentalDTO rental) {

    }

    @Override
    public List<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException {
        return _applicationController.searchForCustomer(customer);
    }

    @Override
    public CopyDTO searchCopyByCopyNumber(int copyNumber) throws RemoteException {
        return null;
    }

    @Override
    public void extendSubscription(CustomerDTO customer) throws RemoteException {
        _applicationController.extendSubscription(customer);
    }

    @Override
    public BookDTO searchBookById(int mediumId) throws RemoteException {
        return _applicationController.searchBookById(mediumId);
    }

    @Override
    public DvdDTO searchDvdById(int mediumId) throws RemoteException {
        return _applicationController.searchDvdById(mediumId);
    }

}
