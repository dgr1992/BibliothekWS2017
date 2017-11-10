package at.fhv.team05.presentation.customer.buttons;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.rmi.RemoteException;

public class ReserveButtonPresenter extends Presenter{
    TableView customerTable;
    IMediumDTO medium;

    @FXML
    public void onReserveButtonPressed() {
        CustomerDTO customer = (CustomerDTO) customerTable.getSelectionModel().getSelectedItem();
        try {
            boolean reservationSuccessful = ClientRun.controller.reserveMedium(medium, customer);
            if (reservationSuccessful) {
                infoAlert("Medium successfully reserved!");
            } else {
                errorAlert("Medium could not be reserved.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void setCustomerTable(TableView<CustomerDTO> customerTable) {
        this.customerTable = customerTable;
    }

    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }
}
