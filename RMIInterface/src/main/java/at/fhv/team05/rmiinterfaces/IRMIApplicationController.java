package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.Enum.ReturnCopyResult;
import at.fhv.team05.dtos.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public interface IRMIApplicationController extends Remote {
    LinkedList<BookDTO> searchForBook(BookDTO book) throws RemoteException;

    LinkedList<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException;

    boolean rentMedium(RentalDTO rental) throws RemoteException;

    List<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException;

    CopyDTO searchCopyByCopyNumber(int copyNumber) throws RemoteException;

    CustomerDTO extendSubscription(CustomerDTO customer) throws RemoteException;

    BookDTO searchBookById(int mediumId) throws RemoteException;

    DvdDTO searchDvdById(int mediumId) throws RemoteException;

    ReturnCopyResult returnCopy(CopyDTO copyDTO) throws RemoteException;

    List<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) throws RemoteException;

    boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) throws RemoteException;

    void reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) throws RemoteException;

    void authenticateUser(String uname, String pw) throws RemoteException;

    CustomerRentalDTO getRentalsFor(CustomerDTO customerDTO) throws  RemoteException;
}
