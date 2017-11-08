package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.dtos.*;
import org.omg.CORBA.portable.RemarshalException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public interface IRMIApplicationController extends Remote {
    LinkedList<BookDTO> searchForBook(BookDTO book) throws RemoteException;

    LinkedList<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException;

    void rentMedium(RentalDTO rental) throws RemoteException;

    List<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException;

    CopyDTO searchCopyByCopyNumber(int copyNumber) throws RemoteException;

    void extendSubscription(CustomerDTO customer) throws RemoteException;

    BookDTO searchBookById(int mediumId) throws RemoteException;

    DvdDTO searchDvdById(int mediumId) throws RemoteException;

//    List<CopyDTO> searchBookCopies(int id);
//
//    List<CopyDTO> searchDvdCopies(int id);

}
