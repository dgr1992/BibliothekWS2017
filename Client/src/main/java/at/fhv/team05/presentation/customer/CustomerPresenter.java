package at.fhv.team05.presentation.customer;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ObjectInterfaces.ICustomer;
import at.fhv.team05.dtos.*;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerPresenter {
    private MainViewPresenter parent;
    private CopyDTO copy;

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
    public void onNextButtonPressed(ActionEvent event) {
        CustomerDTO customer = tblViewCustomer.getSelectionModel().getSelectedItem();
        if (customer != null) {
            parent.openRentalOverview(customer, copy);
        } else {
            infoAlert("Please select a customer.");
        }
    }

    private void infoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.show();
    }


    @FXML
    public void onSearchButtonPressed(ActionEvent event) {
        List<CustomerDTO> customers = new LinkedList<>();
        CustomerDTO customer = new CustomerDTO(getCustomerNumber(), getFirstName(), getLastName());
        try {
            customers.addAll(ClientRun.controller.searchForCustomer(customer));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        initResultTableCustomer(customers);
    }

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

    }

    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
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
    public String getLastName(){
        return txtFieldLastName.getText();
    }

    public void setCopy(CopyDTO copy) {
        this.copy = copy;
    }
}
