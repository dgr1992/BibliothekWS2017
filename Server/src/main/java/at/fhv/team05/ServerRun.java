package at.fhv.team05;

import at.fhv.team05.RMI.RMIApplicationController;
import at.fhv.team05.RMI.RMIFactory;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;
import at.fhv.team05.rmiinterfaces.IRMIFactory;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 */
public class ServerRun {

    public static void main(String[] args) {

        try {

            Registry reg = LocateRegistry.createRegistry(1099);

            RMIFactory rmiFactory = new RMIFactory();

            Naming.bind("rmi://localhost/IRMIFactory", rmiFactory);


        } catch (Exception ex) {
            System.out.println("BookController error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
