package at.fhv.team05.presentation.rental;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.CopyDTO;
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
    CopyDTO copy;

    @FXML
    private TextField txtFieldCopyNumber;

    @FXML
    private StackPane mediumContainer;

    @FXML
    void onNextButtonPressed(ActionEvent event) {

        parent.openCustomerRentalView(copy);
    }


    @FXML
    void onSearchButtonPressed(ActionEvent event) {
        String copyNumber = txtFieldCopyNumber.getText();
        try {
            copy = ClientRun.controller.searchCopyByCopyNumber(copyNumber);
            if (copy != null) {
                if ("book".equalsIgnoreCase(copy.getMediaType())) {
                    BookView book = new BookView();
                    mediumContainer.getChildren().setAll(book.getView());
                    BookPresenter presenter = (BookPresenter) book.getPresenter();
                    presenter.setCopy(copy);
                } else if ("dvd".equalsIgnoreCase(copy.getMediaType())) {
                    DvdView dvd = new DvdView();
                    mediumContainer.getChildren().setAll(dvd.getView());
                    DvdPresenter presenter = (DvdPresenter) dvd.getPresenter();
                    presenter.setCopy(copy);
                }
            } else {
                infoAlert("Could not find specified copy.");
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
