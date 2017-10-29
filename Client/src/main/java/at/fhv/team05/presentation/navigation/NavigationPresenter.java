package at.fhv.team05.presentation.navigation;

import at.fhv.team05.presentation.mainView.MainView;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michelle on 29.10.2017.
 */
public class NavigationPresenter {
    private MainViewPresenter parent;


    @FXML
    void onLoadCustomerOverviewButtonPressed(ActionEvent event) {
        infoAlert();
    }

    @FXML
    void onLoadRentMediumViewButtonPressed(ActionEvent event) {
        infoAlert();
    }

    @FXML
    void onLoadSearchViewButtonPressed(ActionEvent event) {

    }

    @FXML
    void onLogoutButtonPressed(ActionEvent event) {
        parent.changeNavigationBarToLoggedOut();
        parent.openSearchView();
    }


    private void infoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setHeaderText("Not implemented yet!");
        alert.show();
    }

    public void setParent(MainViewPresenter mvp) {
        parent = mvp;
    }




}
