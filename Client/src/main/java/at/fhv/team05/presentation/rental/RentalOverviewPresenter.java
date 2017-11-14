package at.fhv.team05.presentation.rental;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

import javax.annotation.PostConstruct;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Calendar;

public class RentalOverviewPresenter extends Presenter{
    private CopyDTO copy;
    private CustomerDTO customer;

    @FXML
    private TitledPane titledPaneOverview;

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
            if ("book".equalsIgnoreCase(copy.getMediaType())) {
                calendar.add(Calendar.MONTH, 1);
            } else {
                calendar.add(Calendar.DATE, 14);
            }
            Date deadline = new Date(calendar.getTimeInMillis());
            RentalDTO rental = new RentalDTO(copy, customer, today, deadline);
            boolean rentalSuccessful = ClientRun.controller.rentMedium(rental);
            if (rentalSuccessful) {
                infoAlert("Medium successfully rented!");
            } else {
                errorAlert("ERROR! Medium cannot be rented.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onExtendAboButtonPressed(ActionEvent event) {
        try {
            CustomerDTO customerDTO = ClientRun.controller.extendSubscription(customer);
            Calendar c = Calendar.getInstance();
            c.setTime(customerDTO.getPaymentDate());
            c.add(Calendar.YEAR, 1);
            lblAboValidUntil.setText(new Date(c.getTimeInMillis()).toString());
            infoAlert("Subscription successfully extended!");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void initView() {
        titledPaneOverview.setCollapsible(false);
        IMediumDTO medium = null;
        if ("book".equalsIgnoreCase(copy.getMediaType())) {
            try {
                medium = ClientRun.controller.searchBookById(copy.getMediumId());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if ("dvd".equalsIgnoreCase(copy.getMediaType())) {
            try {
                medium = ClientRun.controller.searchDvdById(copy.getMediumId());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (medium != null) {
            lblMediumNumber.setText(Integer.toString(copy.getCopyNumber()));
            lblTitle.setText(medium.getTitle());
            lblCustomerName.setText(customer.getFirstName() + " " + customer.getLastName());
            lblStreet.setText(customer.getAddress().getStreet()+ " " + customer.getAddress().getStreetNumber());
            lblZipCity.setText(customer.getAddress().getZip()+ " / " +customer.getAddress().getCity());
            lblCustomerNumber.setText(Integer.toString(customer.getCustomerId()));
            Date today = new Date(Calendar.getInstance().getTimeInMillis());
            Date paymentDate = customer.getPaymentDate();

            Calendar c = Calendar.getInstance();
            if (paymentDate != null) {
                c.setTime(customer.getPaymentDate());
                c.add(Calendar.YEAR, 1);
            }
            Date subValidUntil = new Date(c.getTimeInMillis());

            if (paymentDate == null) {
                lblAboValidUntil.setText("Customer has not payed yet.");
            } else if ((subValidUntil.before(today) || subValidUntil.equals(today))) {
                lblAboValidUntil.setText("Subscription expired.");
            } else {
                lblAboValidUntil.setText(subValidUntil.toString());
            }

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, 1);
            lblRentedUntil.setText(new Date(calendar.getTimeInMillis()).toString());
        }

    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public void setCopy(CopyDTO copy) {
        this.copy = copy;
    }
}
