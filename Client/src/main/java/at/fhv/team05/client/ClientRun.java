package at.fhv.team05.client;

import at.fhv.team05.client.presentation.mainView.MainView;
import at.fhv.team05.library.ejb.IEJBApplicationController;
import at.fhv.team05.library.rmiinterfaces.IRMIApplicationController;
import at.fhv.team05.library.rmiinterfaces.IRMIFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
//import java.net.MalformedURLException;
//import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;

import javax.ejb.EJB;

public class ClientRun extends Application {
    //public static IRMIApplicationController controller;

    @EJB(name = "ApplicationControllerEJB")
    public static IEJBApplicationController controller;

    public static void main(String[] args) {
        /*try {
            String backendIpAddress = readIpAddressFromConfig();
            IRMIFactory rmiControllerFactory = (IRMIFactory) Naming.lookup("rmi://" + backendIpAddress + "/IRMIFactory");
            controller = rmiControllerFactory.createController();
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }*/
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //If the connection to the server failed then show popup message
        /*if(controller == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Server not running or wrong connection details.");
            alert.show();
            break;
        }*/

        MainView appView = new MainView();

        Scene scene = new Scene(appView.getView());
        stage.setTitle("Library");
        stage.setScene(scene);
        stage.show();
    }

    private static String readIpAddressFromConfig(){
        String ipAddress = null;

        BufferedReader br = null;
        FileReader fr = null;
        try {
            br = new BufferedReader(new FileReader(System.getProperty("user.dir")+ "/Config/client.config"));
            String sCurrentLine;

            //Search for the ip-Address config section
            while ((sCurrentLine = br.readLine()) != null) {
                if(!sCurrentLine.trim().startsWith("#") && sCurrentLine.contains("ip-Address")){
                    ipAddress = sCurrentLine.split("=")[1];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return ipAddress;
    }
}
