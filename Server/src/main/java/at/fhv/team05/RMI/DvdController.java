package at.fhv.team05.RMI;

import at.fhv.team05.domain.Dvd;
import at.fhv.team05.dtos.IDvd;
import at.fhv.team05.persistence.Repository;
import at.fhv.team05.persistence.RepositoryFactory;
import at.fhv.team05.rmiinterfaces.DvdRMI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.LinkedList;

public class DvdController extends UnicastRemoteObject implements DvdRMI {
    private Repository<Dvd> _dvdRepository = null;
    private HashSet<Dvd> _dvdSet;
    private LinkedList<IDvd> _result;

    private static DvdController instance;
    private static final Logger log = LogManager.getLogger(DvdController.class);

    private DvdController() throws RemoteException {
        _dvdRepository = RepositoryFactory.getRepositoryInstance(Dvd.class);
        _dvdSet = new HashSet<>();


    }

    public static DvdController getInstance() throws RemoteException {
        if (instance == null) {
            instance = new DvdController();
        }
        return instance;
    }

    @Override
    public LinkedList<IDvd> searchForDvd(String title, String director, String asin) throws RemoteException {
        return null;
    }
}
