package at.fhv.team05.Application;


import at.fhv.team05.dtos.*;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class ApplicationController implements IRMIApplicationController {
    private static ApplicationController mInstance;
    private BookController _bookController;
    private DvdController _dvdController;
    private CustomerController _customerController;
    private CopyController _copyController;

    private ApplicationController() {
        _bookController = BookController.getInstance();
        _dvdController = DvdController.getInstance();
        _customerController = CustomerController.getInstance();
        _copyController = CopyController.getInstance();
    }

    public static ApplicationController getInstance() {
        if (mInstance == null) {
            mInstance = new ApplicationController();
        }
        return mInstance;
    }

    @Override
    public LinkedList<BookDTO> searchForBook(BookDTO book) throws RemoteException {
        return _bookController.searchForMedium(book);
    }

    @Override
    public LinkedList<DvdDTO> searchForDvd(DvdDTO dvd) throws RemoteException {
        return _dvdController.searchForMedium(dvd);
    }

    @Override
    public void rentMedium(RentalDTO rental) {

    }

    @Override
    public List<CustomerDTO> searchForCustomer(CustomerDTO customer) throws RemoteException {
        return _customerController.searchForCustomer(customer);
    }

    @Override
    public CopyDTO searchCopyByCopyNumber(int copyNumber) throws RemoteException {
        return null;
    }

    @Override
    public void extendSubscription(CustomerDTO customer) throws RemoteException {
        _customerController.extendSubscription(customer);
    }

    @Override
    public BookDTO searchBookById(int mediumId) throws RemoteException {
        return _bookController.searchMediumById(mediumId);
    }

    @Override
    public DvdDTO searchDvdById(int mediumId) throws RemoteException {
        return _dvdController.searchMediumById(mediumId);
    }
}
