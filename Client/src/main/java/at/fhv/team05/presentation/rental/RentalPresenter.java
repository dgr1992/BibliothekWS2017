package at.fhv.team05.presentation.rental;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.dtos.RentalDTO;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import at.fhv.team05.presentation.rental.mediumViews.BookPresenter;
import at.fhv.team05.presentation.rental.mediumViews.BookView;
import at.fhv.team05.presentation.rental.mediumViews.DvdPresenter;
import at.fhv.team05.presentation.rental.mediumViews.DvdView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.rmi.RemoteException;

public class RentalPresenter {
    MainViewPresenter parent;
    IMediumDTO medium;

    @FXML
    private TextField txtFieldMediumNumber;

    @FXML
    private StackPane mediumContainer;

    @FXML
    void onNextButtonPressed(ActionEvent event) {
        parent.openCustomerRentalView(medium);
    }


    @FXML
    void onSearchButtonPressed(ActionEvent event) {
        String mediumNumber = txtFieldMediumNumber.getText();
        try {
            medium = ClientRun.controller.searchMediumByMediumNumber(mediumNumber);
            if (medium != null) {
                if (medium instanceof BookDTO) {
                    BookView book = new BookView();
                    mediumContainer.getChildren().setAll(book.getView());
                    BookPresenter presenter = (BookPresenter) book.getPresenter();
                    presenter.setBookDto(medium);
                } else if (medium instanceof DvdDTO) {
                    DvdView dvd = new DvdView();
                    mediumContainer.getChildren().setAll(dvd.getView());
                    DvdPresenter presenter = (DvdPresenter) dvd.getPresenter();
                    presenter.setDvdDto(medium);
                }
            } else {
                infoAlert("Could not find specified medium.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    private void infoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.show();
    }

    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
    }
}
