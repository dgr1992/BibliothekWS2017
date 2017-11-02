package at.fhv.team05.RMI.DTO;

import at.fhv.team05.domain.Dvd;
import at.fhv.team05.dtos.ICategory;
import at.fhv.team05.dtos.IDvd;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class DvdDTO extends UnicastRemoteObject implements IDvd {
    private Dvd _dvd;

    protected DvdDTO(Dvd dvd) throws RemoteException {
        _dvd = dvd;
    }

    @Override
    public int getId() throws RemoteException {
        return _dvd.getId();
    }

    @Override
    public String getTitle() throws RemoteException {
        return _dvd.getTitle();
    }

    @Override
    public String getAsin() throws RemoteException {
        return _dvd.getAsin();
    }

    @Override
    public Date getReleaseDate() throws RemoteException {
        return _dvd.getReleaseDate();
    }

    @Override
    public ICategory getCategory() throws RemoteException {
        return _dvd.getCategory();
    }

    @Override
    public String getPublisher() throws RemoteException {
        return _dvd.getPublisher();
    }

    @Override
    public String getDirector() throws RemoteException {
        return _dvd.getDirector();
    }
}
