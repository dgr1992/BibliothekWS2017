package at.fhv.team05.presentation.rental;

import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RentalOverviewPresenter {
    private IMediumDTO medium;
    private CustomerDTO customer;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblMediumNumber;

    @FXML
    private Label lblCustomerNumber;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblZipCity;

    @FXML
    private Label lblStreet;

    @FXML
    private Label lblAboValidUntil;

    @FXML
    private Label lblAboRentedUntil;

    @FXML
    void onConfirmButtonPressed(ActionEvent event) {

    }

    @FXML
    void onExtendAboButtonPressed(ActionEvent event) {

    }

    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
