package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.Enum.ReturnCopyResult;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.dtos.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IRMIApplicationController extends Remote {
    ResultListDTO<BookDTO> searchForBook(BookDTO book) throws RemoteException;

    ResultListDTO<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException;

    ResultDTO<Boolean> authenticateUser(String uname, String pw) throws RemoteException;

    ResultDTO<Boolean> rentMedium(RentalDTO rental) throws RemoteException;

    ResultListDTO<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException;

    ResultDTO<CopyDTO> searchCopyByCopyNumber(int copyNumber) throws RemoteException;

    ResultDTO<CustomerDTO> extendSubscription(CustomerDTO customer) throws RemoteException;

    ResultDTO<BookDTO> searchBookById(int mediumId) throws RemoteException;

    ResultDTO<DvdDTO> searchDvdById(int mediumId) throws RemoteException;

    ReturnCopyResult returnCopy(CopyDTO copyDTO) throws RemoteException;

    List<CopyDTO> getCopiesByMedium(IMediumDTO mediumDTO) throws RemoteException;

    boolean checkAvailabilityOfMedium(IMediumDTO mediumDTO) throws RemoteException;

    void reserveMedium(IMediumDTO mediumDTO, CustomerDTO customerDTO) throws RemoteException;

    CustomerRentalDTO getRentalsFor(CustomerDTO customerDTO) throws RemoteException;

    boolean extendRentedMedium(RentalDTO rentalDTO) throws RemoteException;
}
