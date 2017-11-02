package at.fhv.team05;

import at.fhv.team05.presentation.mainView.MainView;
import at.fhv.team05.rmiinterfaces.IRMIApplicationController;
import at.fhv.team05.rmiinterfaces.IRMIFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Hello world!
 */
public class ClientRun extends Application {
    public static void main(String[] args) {

        try {

            IRMIFactory rmiControllerFactory = (IRMIFactory) Naming.lookup("rmi://localhost/IRMIFactory");

        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainView appView = new MainView();

        Scene scene = new Scene(appView.getView());
        stage.setTitle("Bibliothek");
        stage.setScene(scene);
        stage.show();
    }
}
