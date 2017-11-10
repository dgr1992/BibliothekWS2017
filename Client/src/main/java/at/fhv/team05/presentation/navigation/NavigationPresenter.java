package at.fhv.team05.presentation.navigation;

import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.customer.buttons.CustomerButtonType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class NavigationPresenter extends Presenter{


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
        parent.openReservationView();
    }

    @FXML
    void onLogoutButtonPressed(ActionEvent event) {
        parent.changeNavigationBarToLoggedOut();
        parent.openSearchView();
    }

    @FXML
    void onLoadReturnMediumViewButtonPressed(ActionEvent event){parent.openReturnView();}

}
