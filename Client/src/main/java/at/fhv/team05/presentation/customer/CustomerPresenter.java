package at.fhv.team05.presentation.customer;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.customer.buttons.OkButtonPresenter;
import at.fhv.team05.presentation.customer.buttons.OkButtonView;
import at.fhv.team05.presentation.customer.buttons.ReserveButtonPresenter;
import at.fhv.team05.presentation.customer.buttons.ReserveButtonView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class CustomerPresenter extends Presenter {


    @FXML
    private Label lblViewTitle;
    @FXML
    private TextField txtFieldCustomerNumber;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TableView<CustomerDTO> tblViewCustomer;

    @FXML
    private TableColumn<CustomerDTO, String> tblColCustomerNumber;

    @FXML
    private TableColumn<CustomerDTO, String> tblColFirstName;

    @FXML
    private TableColumn<CustomerDTO, String> tblColLastName;

    @FXML
    private TableColumn<CustomerDTO, String> tblColEmail;

    @FXML
    private TableColumn<CustomerDTO, String> tblColPhoneNumber;

    @FXML
    private TableColumn<CustomerDTO, String> tblColDateOfBirth;

    @FXML
    private AnchorPane buttonContainer;

    private boolean detailView;

    /**
    *  Method to show OK button in customer view.
    *  Used for choosing the customer for a rental.
    */
    public void initOkButton(CopyDTO copy) {
        OkButtonView okButtonView = new OkButtonView();
        OkButtonPresenter presenter = (OkButtonPresenter) okButtonView.getPresenter();
        presenter.setCustomerTable(tblViewCustomer);
        presenter.setCopy(copy);
        presenter.setParent(parent);
        buttonContainer.getChildren().setAll(okButtonView.getView());
    }

    /**
    * Method to show the reservation button in the customer view.
    * Used for choosing a customer for a reservation.
    */
    public void initReservationButton(IMediumDTO medium) {
        ReserveButtonView reserveButtonView = new ReserveButtonView();
        ReserveButtonPresenter presenter = (ReserveButtonPresenter) reserveButtonView.getPresenter();
        presenter.setCustomerTable(tblViewCustomer);
        presenter.setMedium(medium);
        presenter.setParent(parent);
        buttonContainer.getChildren().setAll(reserveButtonView.getView());
    }

    /**
    * Searches a customer with the given inputs from the text fields in the customer view.
    */
    @FXML
    public void onSearchButtonPressed(ActionEvent event) {
        List<CustomerDTO> customers = new LinkedList<>();
        CustomerDTO customer = new CustomerDTO(getCustomerNumber(), getFirstName(), getLastName());
        try {
            ResultListDTO<CustomerDTO> resultCustomers = ClientRun.controller.searchForCustomer(customer);
            if (resultCustomers.getException() == null) {
                customers.addAll(resultCustomers.getListDTO());
                initResultTableCustomer(customers);
            } else {
                errorAlert(resultCustomers.getException().getMessage());
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
    * Method for showing the results of searching a customer in the table.
     */
    private void initResultTableCustomer(List<CustomerDTO> customers) {
        ObservableList<CustomerDTO> resultData = FXCollections.observableArrayList();
        resultData.addAll(customers);

        tblColCustomerNumber.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblColFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblColLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tblColPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        tblColDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tblViewCustomer.setItems(resultData);
        tblViewCustomer.setRowFactory(tv -> {
            TableRow<CustomerDTO> row = new TableRow<>();
            row.setOnMouseClicked(e -> {
                if (e.getClickCount() == 2 && (!row.isEmpty())) {
                    if (detailView && tblViewCustomer.getSelectionModel().getSelectedItem() != null) {
                        parent.openCustomerDetailView(tblViewCustomer.getSelectionModel().getSelectedItem());
                    }
                }
            });
            return row;
        });
    }

    public int getCustomerNumber() {
        if (txtFieldCustomerNumber.getText() == null || txtFieldCustomerNumber.getText().isEmpty()) {
            return -1;
        }
        return Integer.valueOf(txtFieldCustomerNumber.getText());
    }

    public String getFirstName() {
        return txtFieldFirstName.getText();
    }

    public String getLastName() {
        return txtFieldLastName.getText();
    }


    public void setDetailView(boolean detailView) {
        this.detailView = detailView;
    }

    public void setViewTitle(String title) {
        lblViewTitle.setText(title);
    }

    public void initialize() {
        //Regular Expression: only numbers are allowed in the text field for customer number
        txtFieldCustomerNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtFieldCustomerNumber.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
