package at.fhv.team05.presentation.navigation;

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
public class NavigationPresenter extends MainViewPresenter {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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
        changeNavigationBarToLoggedOut();
        openSearchView();
    }


    private void infoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setHeaderText("Not implemented yet!");
        alert.show();
    }




}
