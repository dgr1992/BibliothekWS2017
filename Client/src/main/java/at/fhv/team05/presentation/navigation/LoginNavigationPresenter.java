package at.fhv.team05.presentation.navigation;

import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michelle on 29.10.2017.
 */
public class LoginNavigationPresenter {
    MainViewPresenter parent;


    @FXML
    void onLoginButtonPressed(ActionEvent event) {
        parent.changeNavigationBarToLoggedIn();
    }

    public void setParent(MainViewPresenter mvp) {
        parent = mvp;
    }
}
