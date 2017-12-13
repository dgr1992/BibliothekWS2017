package at.fhv.team05.server;

import at.fhv.team05.server.Application.CyclicThreadController;
import at.fhv.team05.server.RMI.RMIFactory;
import at.fhv.team05.server.messaging.ActiveMQCon;
import at.fhv.team05.server.persistence.DatabaseConnection;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRun {

    public static void main(String[] args) {
        try {
           //ActiveMQ Connection
            ActiveMQCon.createConnection("tcp://127.0.0.1:61616", "MessageQueue");

            //Create registry for RMI
            //LocateRegistry.createRegistry(1099);
            CyclicThreadController.getInstance().createRequestForPaymentThread();

            //RMI binding
            RMIFactory rmiFactory = new RMIFactory();
            Naming.bind("rmi://localhost/IRMIFactory", rmiFactory);

            //DatabaseConnection
            DatabaseConnection.init();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
