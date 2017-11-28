package at.fhv.team05;

import at.fhv.team05.RMI.RMIFactory;
import at.fhv.team05.messaging.ActiveMQCon;
import at.fhv.team05.persistence.DatabaseConnection;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRun {

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            RMIFactory rmiFactory = new RMIFactory();
            //ActiveMQ Connection
            ActiveMQCon activeMQCon = ActiveMQCon.getInstance();
            Naming.bind("rmi://localhost/IRMIFactory", rmiFactory);
            DatabaseConnection.init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
