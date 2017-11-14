package at.fhv.team05.presentation.login;

import at.fhv.team05.ClientRun;
import at.fhv.team05.presentation.Presenter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class LoginPresenter extends Presenter implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void loginUser() {
        try {
            if (ClientRun.controller.authenticateUser(getUsr(), getPw())) {
                parent.changeNavigationBarToLoggedIn();
                parent.openSearchView();
            }else {
                infoAlert("Sorry, we could not log you in with those credentials!");
                username.clear();
                password.clear();
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
