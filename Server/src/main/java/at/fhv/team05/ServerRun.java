package at.fhv.team05;

import at.fhv.team05.RMI.SearchController;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Hello world!
 */
public class ServerRun {

    public static void main(String[] args) {

        try {

            LocateRegistry.createRegistry(1099);

            SearchController searchController = new SearchController();

            //Bind this object instance to the name
            Naming.bind("rmi://localhost/SearchController", searchController);

        } catch (Exception ex) {
            System.out.println("SearchController error: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
