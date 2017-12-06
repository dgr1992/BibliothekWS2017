package at.fhv.team05.client.presentation.navigation;

import at.fhv.team05.client.presentation.Presenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginNavigationPresenter extends Presenter{
    @FXML
    void onLoginButtonPressed(ActionEvent event) {
        parent.openLoginView();
    }

    @FXML
    void onSearchButtonPressed(ActionEvent event) {
        parent.openSearchView();
    }

}
