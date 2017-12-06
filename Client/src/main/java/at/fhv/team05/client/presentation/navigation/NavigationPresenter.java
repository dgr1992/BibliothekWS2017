package at.fhv.team05.client.presentation.navigation;

import at.fhv.team05.client.ClientRun;
import at.fhv.team05.client.presentation.Presenter;
import at.fhv.team05.client.presentation.customer.buttons.CustomerButtonType;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Line;

import java.rmi.RemoteException;


public class NavigationPresenter extends Presenter {

    @FXML
    private Line line1;

    @FXML
    private JFXButton searchButton;

    @FXML
    private Line line2;

    @FXML
    private JFXButton customerOverviewButton;

    @FXML
    private Line line3;

    @FXML
    private JFXButton rentMediumButton;

    @FXML
    private Line line4;

    @FXML
    private JFXButton reserveMediumButton;

    @FXML
    private Line line5;

    @FXML
    private JFXButton reservationOverviewButton;

    @FXML
    private Line line6;

    @FXML
    private JFXButton viewMessageButton;

    @FXML
    private Line line7;

    @FXML
    private JFXButton createMessageButton;

    @FXML
    private Line line8;

    @FXML
    private JFXButton logoutButton;

    @FXML
    private Line line9;

    @FXML
    void onLoadCustomerOverviewButtonPressed(ActionEvent event) {
        parent.openCustomerView(null, null, CustomerButtonType.NONE);
    }

    @FXML
    void onLoadRentMediumViewButtonPressed(ActionEvent event) {
        parent.openRentalView();
    }

    @FXML
    void onLoadSearchViewButtonPressed(ActionEvent event) {
        parent.openSearchView();
    }

    @FXML
    void onReserveMediumButtonPressed(ActionEvent event) {
        parent.openReservationView(false);
    }

    @FXML
    void onLogoutButtonPressed(ActionEvent event) {
        parent.changeNavigationBarToLoggedOut();
        parent.openSearchView();
        try {
            ClientRun.controller.logoutUser();
        } catch (Exception e) {
            errorAlert(e.getMessage());
        }
    }

    @FXML
    void onReservationOverviewButtonPressed(ActionEvent event) {
        parent.openReservationView(true);
    }

    @FXML
    void onCreateMessageButtonPressed(ActionEvent event) { parent.openCreateMessageView();}

    @FXML
    void onViewMessageButtonPressed(ActionEvent event) { parent.openViewMessageView();}
}
