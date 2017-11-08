package at.fhv.team05.presentation.rental;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import at.fhv.team05.presentation.mediumViews.BookPresenter;
import at.fhv.team05.presentation.mediumViews.BookView;
import at.fhv.team05.presentation.mediumViews.DvdPresenter;
import at.fhv.team05.presentation.mediumViews.DvdView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RentalPresenter implements Initializable{
    MainViewPresenter parent;
    CopyDTO copy;

    @FXML
    private Label lblTitle;

    @FXML
    private Label labelA;

    @FXML
    private Label labelB;

    @FXML
    private Label labelC;

    @FXML
    private Label labelD;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private TextField txtFieldCopyNumber;

    @FXML
    private StackPane mediumContainer;

    @FXML
    void onNextButtonPressed(ActionEvent event) {
        if (copy != null)
            parent.openCustomerRentalView(copy);
        else
            infoAlert("Please enter a Medium");
    }


    @FXML
    void onSearchButtonPressed(ActionEvent event) {
        int copyNumber = Integer.valueOf(txtFieldCopyNumber.getText());
        try {
            copy = ClientRun.controller.searchCopyByCopyNumber(copyNumber);
            if (copy != null) {
                if ("book".equalsIgnoreCase(copy.getMediaType())) {
                    BookDTO book = ClientRun.controller.searchBookById(copy.getMediumId());
                    lblTitle.setText(book.getTitle());
                    labelA.setText("Author: ");
                    label1.setText(book.getAuthor());
                    labelB.setText("Publisher: ");
                    label2.setText(book.getPublisher());
                    labelC.setText("Release Date: ");
                    label3.setText(book.getReleaseDate().toString());
                    labelD.setText("ISBN: ");
                    label4.setText(book.getIsbn());
                } else if ("dvd".equalsIgnoreCase(copy.getMediaType())) {
                    DvdDTO book = ClientRun.controller.searchDvdById(copy.getMediumId());
                    lblTitle.setText(book.getTitle());
                    labelA.setText("Director: ");
                    label1.setText(book.getDirector());
                    labelB.setText("Publisher: ");
                    label2.setText(book.getPublisher());
                    labelC.setText("Release Date: ");
                    label3.setText(book.getReleaseDate().toString());
                    labelD.setText("ASIN: ");
                    label4.setText(book.getAsin());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtFieldCopyNumber.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtFieldCopyNumber.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }
}
