package at.fhv.team05.presentation.navigation;

import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
