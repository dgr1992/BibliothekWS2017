package at.fhv.team05.Application;


import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class ApplicationController implements IRMIApplicationController {
    private static ApplicationController mInstance;
    private BookController _bookController;
    private DvdController _dvdController;


    private ApplicationController() {
        _bookController = BookController.getInstance();
        _dvdController = DvdController.getInstance();
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
        return null;
    }

    @Override
    public BookDTO searchBookByMediumNumber(String mediumNumber) throws RemoteException {
        return null;
    }

    @Override
    public DvdDTO searchDvdByMediumNumber(String mediumNumber) throws RemoteException {
        return null;
    }
}
