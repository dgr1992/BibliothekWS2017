package at.fhv.team05.presentation.customer;

import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.RentalDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import jfxtras.scene.layout.HBox;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerDetailPresenter {

    @FXML
    private Label lblCustomerNr;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblStreet;

    @FXML
    private Label lblZipCity;

    @FXML
    private Label lblAboUntil;

    @FXML
    private TableColumn<RentalDTO, Integer> tblColCopyNrCurrent;

    @FXML
    private TableColumn<RentalDTO, String> tblColTitleCurrent;

    @FXML
    private TableColumn<RentalDTO, Date> tblColPickUpDateCurrent;

    @FXML
    private TableColumn<RentalDTO, Date> tblColDeadline;

    @FXML
    private TableColumn<RentalDTO, Integer> tblColExtensions;

    @FXML
    private TableColumn<RentalDTO, Integer> tblColCopyNrHistory;

    @FXML
    private TableColumn<RentalDTO, String> tblColTitleHistory;

    @FXML
    private TableColumn<RentalDTO, Date> tblColPickupDateHistory;

    @FXML
    private TableColumn<RentalDTO, Date> tblColReturnDate;

    CustomerDTO customer;


    public void setCustomerDetails(){
        lblCustomerNr.setText(Integer.toString(customer.getCustomerId()));
        lblFirstName.setText(customer.getFirstName());
        lblLastName.setText(customer.getLastName());
        lblStreet.setText(customer.getAddress().getStreet() + " " + customer.getAddress().getStreetNumber());
        lblZipCity.setText(customer.getAddress().getZip() + " / " + customer.getAddress().getCity());
        lblAboUntil.setText(customer.getPaymentDate().toString());

    }

    public void fillTable(){

    }


    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

}
