package at.fhv.team05.presentation.navigation;

import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;


public class NavigationPresenter extends Presenter{

    private MainViewPresenter parent;


    @FXML
    void onLoadCustomerOverviewButtonPressed(ActionEvent event) {
        parent.openCustomerView(null, false);
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

    public void setParent(MainViewPresenter mvp) {
        parent = mvp;
    }

}
