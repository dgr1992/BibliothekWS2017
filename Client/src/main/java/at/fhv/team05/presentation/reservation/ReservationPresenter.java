package at.fhv.team05.presentation.reservation;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.dtos.ReservationDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

public class ReservationPresenter extends Presenter{
    private IMediumDTO medium;

    @FXML
    private Label lblTitle;

    @FXML
    private TableView<ReservationDTO> tblViewReservations;

    @FXML
    private TableColumn<ReservationDTO, String> tblColCustomerNumber;

    @FXML
    private TableColumn<ReservationDTO, String> tblColCostumerName;

    @FXML
    private TableColumn<ReservationDTO, Date> tblColReservationDate;

    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }

    /**
     * Initialized the view
     */
    public void initView() {
        lblTitle.setText(medium.getTitle());
        try {
            //Loads all reservations for given medium
            ResultListDTO<ReservationDTO> resultReservation = ClientRun.controller.getReservationsByMedium(medium);

            //Checks if reservations were successfully loaded.
            if (resultReservation.getException() == null) {
                fillTable(resultReservation.getListDTO());
            } else {
                errorAlert(resultReservation.getException().getMessage());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills the table with the Reservation information.
     * @param listDTO Reservations for given medium.
     */
    private void fillTable(List<ReservationDTO> listDTO) {
        ObservableList<ReservationDTO> resultData = FXCollections.observableArrayList();
        resultData.addAll(listDTO);
        tblColCustomerNumber.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue().getCustomer().getCustomerId())));
        tblColCostumerName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCustomer().getFirstName() + " " + data.getValue().getCustomer().getLastName()));
        tblColReservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));

        //Choose ascending for sort-type
        tblColReservationDate.setSortType(TableColumn.SortType.ASCENDING);

        tblViewReservations.setItems(resultData);

        //Orders table by reservation date.
        tblViewReservations.getSortOrder().add(tblColReservationDate);
    }
}
