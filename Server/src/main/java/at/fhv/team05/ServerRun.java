package at.fhv.team05;

import at.fhv.team05.Application.CyclicThreadController;
import at.fhv.team05.RMI.RMIFactory;
import at.fhv.team05.messaging.ActiveMQCon;
import at.fhv.team05.persistence.DatabaseConnection;
import org.apache.activemq.ActiveMQConnection;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ServerRun {

    public static void main(String[] args) {
        try {
            //ActiveMQ Connection
            ActiveMQCon.createConnection(ActiveMQConnection.DEFAULT_BROKER_URL, "MessageQueue");


            //Create registry for RMI
            LocateRegistry.createRegistry(1099);
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
