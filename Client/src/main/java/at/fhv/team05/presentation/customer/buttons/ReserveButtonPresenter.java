package at.fhv.team05.presentation.customer.buttons;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ResultDTO;
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
