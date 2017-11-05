package at.fhv.team05.rmiinterfaces;

import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.RentalDTO;
import org.omg.CORBA.portable.RemarshalException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public interface IRMIApplicationController extends Remote {
    LinkedList<BookDTO> searchForBook(BookDTO book) throws RemoteException;

    LinkedList<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException;

    void rentMedium(RentalDTO rental) throws RemoteException;

    List<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException;

    BookDTO searchBookByMediumNumber(String mediumNumber) throws RemoteException;

    DvdDTO searchDvdByMediumNumber(String mediumNumber) throws RemoteException;
}
