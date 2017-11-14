package at.fhv.team05.presentation.login;

import at.fhv.team05.ClientRun;
import at.fhv.team05.presentation.Presenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class LoginPresenter extends Presenter implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void loginUser() {
        try {
            if (ClientRun.controller.authenticateUser(getUsr(), getPw())) {
                parent.changeNavigationBarToLoggedIn();
                parent.openSearchView();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    private String getUsr() {
        return username.getText();
    }

    private String getPw() {
        return password.getText();
    }


}
