package at.fhv.team05.presentation.customer.buttons;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.rmi.RemoteException;

public class ReserveButtonPresenter extends Presenter {
    private TableView customerTable;
    private IMediumDTO medium;

    @FXML
    public void onReserveButtonPressed() {
        CustomerDTO customer = (CustomerDTO) customerTable.getSelectionModel().getSelectedItem();
        try {
            boolean available = ClientRun.controller.checkAvailabilityOfMedium(medium);
            if (available) {
                infoAlert("Medium is available in library.");
                return;
            }
            if (customer != null) {
                ClientRun.controller.reserveMedium(medium, customer);
            } else {
                infoAlert("Please select a customer.");
                return;
            }
            infoAlert("Medium successfully reserved!");

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
