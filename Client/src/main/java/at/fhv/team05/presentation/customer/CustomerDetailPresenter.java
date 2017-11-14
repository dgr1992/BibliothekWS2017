package at.fhv.team05.presentation.customer;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.CustomerDTO;
import at.fhv.team05.dtos.CustomerRentalDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import jfxtras.scene.layout.HBox;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.ResourceBundle;

public class CustomerDetailPresenter extends Presenter {

    @FXML
    private TableView<RentalDTO> tableViewCurrent;

    @FXML
    private TableView<RentalDTO> tableViewHistory;

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
    private TableColumn<RentalDTO, String> tblColCopyNrCurrent;

    @FXML
    private TableColumn<RentalDTO, String> tblColTitleCurrent;

    @FXML
    private TableColumn<RentalDTO, Date> tblColPickUpDateCurrent;

    @FXML
    private TableColumn<RentalDTO, Date> tblColDeadline;

    @FXML
    private TableColumn<RentalDTO, Integer> tblColExtensions;

    @FXML
    private TableColumn<RentalDTO, String> tblColCopyNrHistory;

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
        try {
            fillTable(ClientRun.controller.getRentalsFor(customer));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void fillTable(CustomerRentalDTO customerRentalDTO){
        tblColCopyNrCurrent.setCellValueFactory(param -> new SimpleStringProperty (Integer.toString(param.getValue().getCopy().getCopyNumber())));
        tblColCopyNrHistory.setCellValueFactory(param -> new SimpleStringProperty(Integer.toString(param.getValue().getCopy().getCopyNumber())));
        tblColPickUpDateCurrent.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        tblColPickupDateHistory.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        tblColDeadline.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        tblColExtensions.setCellValueFactory(new PropertyValueFactory<>("extendCount"));
        tblColReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        tblColTitleCurrent.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCopy().getMediaType()));
        tblColTitleHistory.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCopy().getMediaType()));
        ObservableList<RentalDTO> resultDataCurrent = FXCollections.observableArrayList();
        resultDataCurrent.addAll(customerRentalDTO.getCurrent());
        tableViewCurrent.setItems(resultDataCurrent);
        ObservableList<RentalDTO> resultDataHistory = FXCollections.observableArrayList();
        resultDataHistory.addAll(customerRentalDTO.getHistory());
        tableViewHistory.setItems(resultDataHistory);

        tableViewCurrent.setRowFactory((Callback<TableView<RentalDTO>, TableRow<RentalDTO>>) tableView -> {
            final TableRow<RentalDTO> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem extendRental = new MenuItem("Extend Rental");
            final MenuItem returnRental = new MenuItem("Return Rental");
            extendRental.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    infoAlert("test");
                }
            });
            contextMenu.getItems().add(extendRental);
            contextMenu.getItems().add(returnRental);

            returnRental.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //TODO Rückgabe (dagro)
                }
            });

            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu)null)
                            .otherwise(contextMenu)
            );
            return row ;
        });

    }


    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

}
