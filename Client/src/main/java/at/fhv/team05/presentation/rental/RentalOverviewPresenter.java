package at.fhv.team05.presentation.rental;

import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Calendar;
import java.util.Date;

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
    private Label lblRentedUntil;

    @FXML
    void onConfirmButtonPressed(ActionEvent event) {

    }

    @FXML
    void onExtendAboButtonPressed(ActionEvent event) {

    }

    public void initialize() {
        if(medium!=null) {
            lblMediumNumber.setText(Integer.toString(medium.getId()));
            lblTitle.setText(medium.getTitle());
            lblCustomerName.setText(customer.getFirstName() + customer.getLastName());
            //lblZipCity.setText(customer.getAddressId());
            lblCustomerNumber.setText(Integer.toString(customer.getCustomerId()));
            Calendar c = Calendar.getInstance();
            c.setTime(customer.getPaymentDate());
            c.add(Calendar.YEAR, 1);
            lblAboValidUntil.setText(c.getTime().toString());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH,1);
            lblRentedUntil.setText(calendar.getTime().toString());
        }

    }



    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
