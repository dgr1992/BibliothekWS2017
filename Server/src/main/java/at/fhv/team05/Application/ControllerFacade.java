package at.fhv.team05.Application;


import at.fhv.team05.Application.medium.BookController;
import at.fhv.team05.Application.medium.DvdController;
import at.fhv.team05.domain.Address;
import at.fhv.team05.dtos.*;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class ControllerFacade implements IRMIApplicationController {
    private static ControllerFacade mInstance;
    private BookController _bookController;
    private DvdController _dvdController;
    private CustomerController _customerController;
    private CopyController _copyController;
    private AddressController _addressController;

    private ControllerFacade() {
        _bookController = BookController.getInstance();
        _dvdController = DvdController.getInstance();
        _customerController = CustomerController.getInstance();
        _copyController = CopyController.getInstance();
        _addressController = AddressController.getInstance();
    }

    public static ControllerFacade getInstance() {
        if (mInstance == null) {
            mInstance = new ControllerFacade();
        }
        return mInstance;
    }

    @Override
    public LinkedList<BookDTO> searchForBook(BookDTO book) throws RemoteException {
        return _bookController.searchFor(book);
    }

    @Override
    public LinkedList<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException {
        return _dvdController.searchFor(dvd);
    }

    @Override
    public void rentMedium(RentalDTO rental) {

    }

    @Override
    public List<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException {
        return _customerController.searchFor(customer);
    }

    @Override
    public CopyDTO searchCopyByCopyNumber(int copyNumber) throws RemoteException {
        return _copyController.searchCopyByCopyNumber(copyNumber);
    }

    @Override
    public void extendSubscription(CustomerDTO customer) throws RemoteException {
        _customerController.extendSubscription(customer);
    }

    @Override
    public BookDTO searchBookById(int mediumId) throws RemoteException {
        return _bookController.searchById(mediumId);
    }

    @Override
    public DvdDTO searchDvdById(int mediumId) throws RemoteException {
        return _dvdController.searchById(mediumId);
    }

    public Address getDomainAddress(AddressDTO address) {
        return _addressController.getDomain(address);
    }
}
