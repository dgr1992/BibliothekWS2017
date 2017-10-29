package at.fhv.team05.presentation.navigation;

import at.fhv.team05.presentation.mainView.MainViewPresenter;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michelle on 29.10.2017.
 */
public class LoginNavigationPresenter extends MainViewPresenter{

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void onLoginButtonPressed(ActionEvent event) {
        changeNavigationBarToLoggedIn();
    }
}
