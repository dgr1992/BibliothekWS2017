package at.fhv.team05.server;

import java.rmi.Naming;

import at.fhv.team05.server.Application.ControllerFacade;
import at.fhv.team05.server.Application.CyclicThreadController;
import at.fhv.team05.server.RMI.RMIFactory;
import at.fhv.team05.server.messaging.ActiveMQCon;
import at.fhv.team05.server.persistence.DatabaseConnection;

/**
 * Created by daniel on 20.12.17.
 */
public class Server {
    private static Server _theInstance;
    private ControllerFacade _controllerFacade;

    private Server(){
        //ActiveMQ Connection
        ActiveMQCon.createConnection("tcp://127.0.0.1:61616", "MessageQueue");

        //Create registry for RMI
        //LocateRegistry.createRegistry(1099);
        CyclicThreadController.getInstance().createRequestForPaymentThread();

        //RMI binding
        try {
            RMIFactory rmiFactory = new RMIFactory();
            Naming.bind("rmi://localhost/IRMIFactory", rmiFactory);
        }catch(Exception ex){
            System.out.println("Failed to start RMI");
        }

        //DatabaseConnection
        DatabaseConnection.init();

        _controllerFacade = ControllerFacade.getInstance();
    }

    public static Server getInstance(){
        if(_theInstance == null){
            _theInstance = new Server();
        }
        return _theInstance;
    }

    public ControllerFacade getControllerFacade(){
        return _controllerFacade;
    }
}
