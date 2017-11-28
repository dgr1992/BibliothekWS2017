package at.fhv.team05.presentation.navigation;

import at.fhv.team05.ClientRun;
import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.customer.buttons.CustomerButtonType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.rmi.RemoteException;


public class NavigationPresenter extends Presenter {


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
        } catch (RemoteException e) {
            errorAlert(e.getMessage());
        }
    }

    @FXML
    void onReservationOverviewButtonPressed(ActionEvent event) {
        parent.openReservationView(true);
    }
}
