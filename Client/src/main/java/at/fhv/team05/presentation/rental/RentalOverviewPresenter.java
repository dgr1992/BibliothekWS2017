package at.fhv.team05.presentation.rental;

import at.fhv.team05.dtos.*;
import at.fhv.team05.ClientRun;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sun.util.resources.cldr.aa.CalendarData_aa_ER;

import javax.security.auth.callback.Callback;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

import java.util.Calendar;

public class RentalOverviewPresenter {
    private CopyDTO copy;
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
    private Button btnExtendSub;

    @FXML
    void onConfirmButtonPressed(ActionEvent event) {
        try {
            Date today = new Date(Calendar.getInstance().getTime().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH,1);
            Date deadline = new Date(calendar.getTime().getTime());
            RentalDTO rental = new RentalDTO(12, customer.getId(), today, deadline);
            ClientRun.controller.rentMedium(rental);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onExtendAboButtonPressed(ActionEvent event) {
        try {
            ClientRun.controller.extendAbo(customer);
            infoAlert("Subscription successfully extended!");
            btnExtendSub.setDisable(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        //TODO get medium
//        if(medium!=null) {
//            lblMediumNumber.setText(Integer.toString(medium.getId()));
//            lblTitle.setText(medium.getTitle());
//            lblCustomerName.setText(customer.getFirstName() + customer.getLastName());
//            //lblZipCity.setText(customer.getAddressId());
//            lblCustomerNumber.setText(Integer.toString(customer.getCustomerId()));
//            Calendar c = Calendar.getInstance();
//            c.setTime(customer.getPaymentDate());
//            c.add(Calendar.YEAR, 1);
//            lblAboValidUntil.setText(c.getTime().toString());
//            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.MONTH,1);
//            lblRentedUntil.setText(calendar.getTime().toString());
//        }

    }
    
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }


    private void infoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.show();
    }

    public void setCopy(CopyDTO copy) {
        this.copy = copy;
    }
}
