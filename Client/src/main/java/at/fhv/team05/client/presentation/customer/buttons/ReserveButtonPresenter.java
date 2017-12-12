package at.fhv.team05.client.presentation.customer.buttons;

import at.fhv.team05.client.ClientRun;
import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.library.dtos.CustomerDTO;
import at.fhv.team05.library.dtos.IMediumDTO;
import at.fhv.team05.client.presentation.Presenter;
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
            if (customer != null) {
                ResultDTO<Boolean> resultDto = ClientRun.controller.reserveMedium(medium, customer);
                if (resultDto.getException() != null) {
                    errorAlert(resultDto.getException().getMessage());
                }
            } else {
                infoAlert("Please select a customer.");
                return;
            }
            infoAlert("Medium successfully reserved!");
            parent.openReservationView(false);

        } catch (Exception e) {
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
