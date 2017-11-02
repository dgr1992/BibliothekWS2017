package at.fhv.team05;

import at.fhv.team05.RMI.RMIApplicationController;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 */
public class ServerRun {

    public static void main(String[] args) {

        try {

            Registry reg = LocateRegistry.createRegistry(1099);

            //Bind this object instance to the name
            Naming.bind("rmi://localhost/ApplicationController", new RMIApplicationController());

        } catch (Exception ex) {
            System.out.println("BookController error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
