package at.fhv.team05.presentation.rental;

import at.fhv.team05.ClientRun;
import at.fhv.team05.Enum.MediaLoanPeriod;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.dtos.*;
import at.fhv.team05.presentation.Presenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RentalOverviewPresenter extends Presenter {
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

    /**
     * Confirms the rental for which the overview is displayed.
     */
    @FXML
    void onConfirmButtonPressed(ActionEvent event) {
        try {
            ResultDTO<ConfigurationDataDTO> loadPeriodResult;

            //Load rental period depending on the type of a copy
            if ("book".equalsIgnoreCase(copy.getMediaType())) {
                loadPeriodResult = ClientRun.controller.getLoanPeriodFor(MediaLoanPeriod.Book);
            } else {
                loadPeriodResult = ClientRun.controller.getLoanPeriodFor(MediaLoanPeriod.DVD);
            }

            if (loadPeriodResult.getException() == null) {
                //add loan period to today's date to get the duration for the rental.
                Integer loanPeriod = Integer.valueOf(loadPeriodResult.getDto().getValue());
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, loanPeriod);

                //Transform Calendar to java.sql.Date
                Date deadline = new Date(calendar.getTimeInMillis());
                Date today = new Date(Calendar.getInstance().getTimeInMillis());

                //Create RentalDTO for this rental.
                RentalDTO rental = new RentalDTO(copy, customer, today, deadline);

                //Rent medium
                ResultDTO<Boolean> resultDTO = ClientRun.controller.rentMedium(rental);

                //Check if rental was successful.
                if (resultDTO.getDto()) {
                    infoAlert("Medium successfully rented!");
                } else {
                    errorAlert("ERROR! " + resultDTO.getException().getMessage());
                }
            } else {
                errorAlert(loadPeriodResult.getException().getMessage());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extends the subscription for the chosen customer.
     */
    @FXML
    void onExtendAboButtonPressed(ActionEvent event) {
        try {
            //Extend subscription
            ResultDTO<CustomerDTO> resultCustomer = ClientRun.controller.extendSubscription(customer);
            if (resultCustomer.getException() == null) {
                //reload date until subscription is valid.
                CustomerDTO customer = resultCustomer.getDto();
                Calendar c = Calendar.getInstance();
                c.setTime(customer.getPaymentDate());
                c.add(Calendar.YEAR, 1);
                lblAboValidUntil.setText(new Date(c.getTimeInMillis()).toString());
                infoAlert("Subscription successfully extended!");
            } else {
                errorAlert(resultCustomer.getException().getMessage());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays all information for the overview of a rental.
     */
    public void initView() {
        titledPaneOverview.setCollapsible(false);
        ResultDTO<IMediumDTO> medium = new ResultDTO<>();

        //Load medium depending on the ID of the copy which should be rented.
        if ("book".equalsIgnoreCase(copy.getMediaType())) {
            try {
                medium.setDto(ClientRun.controller.searchBookById(copy.getMediumId()).getDto());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if ("dvd".equalsIgnoreCase(copy.getMediaType())) {
            try {
                medium.setDto(ClientRun.controller.searchDvdById(copy.getMediumId()).getDto());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        if (medium.getDto() != null) {
            //Display information about the medium
            lblMediumNumber.setText(Integer.toString(copy.getCopyNumber()));
            lblTitle.setText(medium.getDto().getTitle());

            //Display information about the customer.
            lblCustomerName.setText(customer.getFirstName() + " " + customer.getLastName());
            lblStreet.setText(customer.getAddress().getStreet() + " " + customer.getAddress().getStreetNumber());
            lblZipCity.setText(customer.getAddress().getZip() + " " + customer.getAddress().getCity());
            lblCustomerNumber.setText(Integer.toString(customer.getCustomerId()));

            //Display duration for subscription
            Date today = new Date(Calendar.getInstance().getTimeInMillis());
            Date paymentDate = customer.getPaymentDate();

            Calendar c = Calendar.getInstance();

            //Calculate duration for subscription if customer has already payed.
            if (paymentDate != null) {
                c.setTime(customer.getPaymentDate());
                c.add(Calendar.YEAR, 1);
            }
            Date subValidUntil = new Date(c.getTimeInMillis());

            //Check if customer has not payed yet or if subscription expired.
            if (paymentDate == null) {
                lblAboValidUntil.setText("Customer has not payed yet.");
            } else if ((subValidUntil.before(today) || subValidUntil.equals(today))) {
                lblAboValidUntil.setText("Subscription expired.");
            } else {
                lblAboValidUntil.setText(subValidUntil.toString());
            }

            //Display until the copy is rented
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
