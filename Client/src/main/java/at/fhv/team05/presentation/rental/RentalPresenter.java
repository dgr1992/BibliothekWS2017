package at.fhv.team05.presentation.rental;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.rmi.RemoteException;

public class RentalPresenter {
    MainViewPresenter parent;

    @FXML
    private TextField txtFieldMediumNumber;

    @FXML
    private StackPane mediumContainer;

    @FXML
    void onNextButtonPressed(ActionEvent event) {
        parent.openCustomerRentalView();
    }


    @FXML
    void onSearchButtonPressed(ActionEvent event) {
        String mediumNumber = txtFieldMediumNumber.getText();
        try {
            IMediumDTO medium = ClientRun.controller.searchMediumByMediumNumber(mediumNumber);
            if (medium instanceof BookDTO) {

            } else {

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
    }
}
