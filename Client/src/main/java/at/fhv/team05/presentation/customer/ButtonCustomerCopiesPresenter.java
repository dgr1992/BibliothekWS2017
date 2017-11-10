package at.fhv.team05.presentation.customer;

import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ButtonCustomerCopiesPresenter extends Presenter{
    TableView customerTable;
    MainViewPresenter parent;

    @FXML
    void onOkButtonPressed(ActionEvent event) {
        CustomerDTO customer = (CustomerDTO) customerTable.getSelectionModel().getSelectedItem();
        if (customer != null) {
            //parent.openRentalOverview(customer, copy);
        } else {
            infoAlert("Please select a customer.");
        }
    }

    public void setCustomerTable(TableView customerTable) {
        this.customerTable = customerTable;
    }

    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
    }
}
