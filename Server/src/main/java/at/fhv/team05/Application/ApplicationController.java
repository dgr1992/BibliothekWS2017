package at.fhv.team05.Application;


import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;

import java.rmi.RemoteException;
import java.util.LinkedList;

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
    public LinkedList<BookDTO> searchForBook(String title, String author, String ISBN) throws RemoteException {
        return _bookController.searchForBook(title, author, ISBN);
    }

    @Override
    public LinkedList<DvdDTO> searchForDvd(String title, String director, String asin) throws RemoteException {
        return _dvdController.searchForDvd(title, director, asin);
    }

    @Override
    public void rentMedium(RentalDTO rental) {

    }
}
