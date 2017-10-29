package at.fhv.team05.presentation.navigation;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Michelle on 29.10.2017.
 */
public class NavigationPresenter {
    @FXML
    private JFXButton btnLoadSearchView;

    @FXML
    private JFXButton btnLoadCustomerOverview;

    @FXML
    private JFXButton btnLoadRentMediumView;

    @FXML
    private AnchorPane navigationContainer;

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


    private void infoAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Information");
        alert.setHeaderText("Not implemented yet!");
        alert.show();
    }




}
