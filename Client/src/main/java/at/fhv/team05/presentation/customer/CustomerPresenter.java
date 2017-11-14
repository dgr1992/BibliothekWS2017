package at.fhv.team05.presentation.customer;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.*;
import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.customer.buttons.OkButtonPresenter;
import at.fhv.team05.presentation.customer.buttons.OkButtonView;
import at.fhv.team05.presentation.customer.buttons.ReserveButtonPresenter;
import at.fhv.team05.presentation.customer.buttons.ReserveButtonView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class CustomerPresenter extends Presenter{


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


    public void initOkButton(CopyDTO copy) {
        OkButtonView okButtonView = new OkButtonView();
        OkButtonPresenter presenter = (OkButtonPresenter) okButtonView.getPresenter();
        presenter.setCustomerTable(tblViewCustomer);
        presenter.setCopy(copy);
        presenter.setParent(parent);
        buttonContainer.getChildren().setAll(okButtonView.getView());
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


    public void initReservationButton(IMediumDTO medium) {
        ReserveButtonView reserveButtonView = new ReserveButtonView();
        ReserveButtonPresenter presenter = (ReserveButtonPresenter)  reserveButtonView.getPresenter();
        presenter.setCustomerTable(tblViewCustomer);
        presenter.setMedium(medium);
        buttonContainer.getChildren().setAll(reserveButtonView.getView());
    }

    public void setViewTitle(String title) {
        lblViewTitle.setText(title);
    }
}
