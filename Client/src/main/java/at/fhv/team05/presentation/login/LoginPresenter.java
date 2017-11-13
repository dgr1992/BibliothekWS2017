package at.fhv.team05.presentation.login;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPresenter implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private TextField password;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private boolean loginUser() {



        return false;
    }



    private String getUsr() {
        return username.getText();
    }

    private String getPw() {
        return password.getText();
    }


}
