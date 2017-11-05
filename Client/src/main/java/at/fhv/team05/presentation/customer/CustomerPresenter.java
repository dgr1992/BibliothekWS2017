package at.fhv.team05.presentation.customer;

import at.fhv.team05.ObjectInterfaces.ICustomer;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerPresenter {
    private MainViewPresenter parent;
    private IMediumDTO medium;

    @FXML
    private TextField txtFieldCustomerNumber;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastName;

    @FXML
    private TableView<?> tblViewCustomer;

    @FXML
    private TableColumn<?, ?> tblColCustomerNumber;

    @FXML
    private TableColumn<?, ?> tblColName;

    @FXML
    private TableColumn<?, ?> tblColAddress;

    @FXML
    private TableColumn<?, ?> tblColEmail;

    @FXML
    private TableColumn<?, ?> tblColPhoneNumber;

    @FXML
    private TableColumn<?, ?> tblColDateOfBirth;

    @FXML
    void onNextButtonPressed(ActionEvent event) {
        //TODO check if any customer is selected
        CustomerDTO customer = new CustomerDTO(12, "Max", "Mustermann");
        parent.openRentalOverview(customer, medium);
    }


    @FXML
    void onSearchButtonPressed(ActionEvent event) {
        List<CustomerDTO> customers = new LinkedList<>();
    }

    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
    }


    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }
}
