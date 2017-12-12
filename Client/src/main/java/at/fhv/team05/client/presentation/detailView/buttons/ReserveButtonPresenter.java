package at.fhv.team05.client.presentation.detailView.buttons;

import at.fhv.team05.client.ClientRun;
import at.fhv.team05.library.dtos.IMediumDTO;
import at.fhv.team05.client.presentation.Presenter;
import at.fhv.team05.client.presentation.customer.buttons.CustomerButtonType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.rmi.RemoteException;

public class ReserveButtonPresenter extends Presenter{
    private IMediumDTO medium;

    @FXML
    void onReserveMediumButtonPressed(ActionEvent event) {
        try {
            boolean available = ClientRun.controller.checkAvailabilityOfMedium(medium);
            if (available) {
                infoAlert("Medium is available in library.");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        parent.openCustomerView(null, medium, CustomerButtonType.RESERVATION);
    }

    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }
}
