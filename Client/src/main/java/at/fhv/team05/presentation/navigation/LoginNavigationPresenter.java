package at.fhv.team05.presentation.navigation;

import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginNavigationPresenter extends Presenter{
    @FXML
    void onLoginButtonPressed(ActionEvent event) {
        parent.changeNavigationBarToLoggedIn();
    }

}
