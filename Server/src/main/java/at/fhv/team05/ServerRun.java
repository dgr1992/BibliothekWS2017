package at.fhv.team05;

import at.fhv.team05.RMI.RMIFactory;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRun {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            RMIFactory rmiFactory = new RMIFactory();
            Naming.bind("rmi://localhost/IRMIFactory", rmiFactory);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
