package at.fhv.team05.Application;


import at.fhv.team05.dtos.IBook;
import at.fhv.team05.dtos.IDvd;
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
    public LinkedList<IBook> searchForBook(String title, String author, String ISBN) throws RemoteException {
        return _bookController.searchForBook(title, author, ISBN);
    }

    @Override
    public LinkedList<IDvd> searchForDvd(String title, String director, String asin) throws RemoteException {
        return _dvdController.searchForDvd(title, director, asin);
    }
}
