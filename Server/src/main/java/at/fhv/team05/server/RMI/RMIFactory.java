package at.fhv.team05.server.RMI;

import at.fhv.team05.library.rmiinterfaces.IRMIApplicationController;
import at.fhv.team05.library.rmiinterfaces.IRMIFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIFactory extends UnicastRemoteObject implements IRMIFactory {

    public RMIFactory() throws RemoteException {
        super();
    }

    @Override
    public IRMIApplicationController createController() throws RemoteException {
        return new RMIApplicationController();
    }
}
