package at.fhv.team05.library.rmiinterfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIFactory extends Remote {

    IRMIApplicationController createController() throws RemoteException;

}
