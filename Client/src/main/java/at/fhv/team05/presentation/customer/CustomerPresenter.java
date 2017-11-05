package at.fhv.team05.presentation.customer;

import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerPresenter {
    private MainViewPresenter parent;


    @FXML
    private TextField txtFieldNumberName;

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
        parent.openRentalOverview();
    }

    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
    }


}
