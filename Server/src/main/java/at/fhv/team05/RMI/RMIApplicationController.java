package at.fhv.team05.RMI;

import at.fhv.team05.Application.ControllerFacade;
import at.fhv.team05.Enum.ReturnCopyResult;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.dtos.*;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class RMIApplicationController extends UnicastRemoteObject implements IRMIApplicationController {
    private ControllerFacade _controllerFacade;

    public RMIApplicationController() throws RemoteException {
        _controllerFacade = ControllerFacade.getInstance();
    }

    @Override
    public LinkedList<BookDTO> searchForBook(BookDTO book) throws RemoteException {
        return _controllerFacade.searchForBook(book);
    }

    @Override
    public LinkedList<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException {
        return _controllerFacade.searchForDvd(dvd);
    }

    @Override
    public ResultDTO<Boolean> rentMedium(RentalDTO rental) {
        return _controllerFacade.rentMedium(rental);
    }

    @Override
    public List<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException {
        return _controllerFacade.searchForCustomer(customer);
    }

    @Override
    public CopyDTO searchCopyByCopyNumber(int copyNumber) throws RemoteException {
        return _controllerFacade.searchCopyByCopyNumber(copyNumber);
    }

    @Override
    public CustomerDTO extendSubscription(CustomerDTO customer) throws RemoteException {
        return _controllerFacade.extendSubscription(customer);
    }

    @Override
    public BookDTO searchBookById(int mediumId) throws RemoteException {
        return _controllerFacade.searchBookById(mediumId);
    }

    @Override
    public DvdDTO searchDvdById(int mediumId) throws RemoteException {
        return _controllerFacade.searchDvdById(mediumId);
    }

    @Override
    public ReturnCopyResult returnCopy(CopyDTO copyDTO) throws RemoteException {
        return _controllerFacade.returnCopy(copyDTO);
    }

    @Override
    public List<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) {
        return _controllerFacade.getCopiesByMedium(mediumDTO);
    }

    @Override
    public boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) throws RemoteException {
        return _controllerFacade.checkAvailabilityOfMedium(mediumDTO);
    }

    @Override
    public void reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) throws RemoteException {
        _controllerFacade.reserveMedium(mediumDTO, customerDTO);
    }

    @Override
    public CustomerRentalDTO getRentalsFor(CustomerDTO customerDTO) throws RemoteException {
        return _controllerFacade.getRentalsFor(customerDTO);
    }

    @Override
    public boolean authenticateUser(String uname, String pw) throws RemoteException {
        return _controllerFacade.authenticateUser(uname, pw);
    }

}
