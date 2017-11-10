package at.fhv.team05.presentation.rental;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RentalPresenter extends Presenter implements Initializable {
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
    private Label labelE;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private TextField txtFieldCopyNumber;

    @FXML
    void onNextButtonPressed(ActionEvent event) {
        if (copy != null) {
            if (copy.getRental() == null) {
                parent.openCustomerView(copy, true);
            }else {
                infoAlert("This Medium is already rented");
            }
        } else
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
                    labelE.setText("Copy Number: ");
                    label5.setText(String.valueOf(copy.getCopyNumber()));

                } else if ("dvd".equalsIgnoreCase(copy.getMediaType())) {
                    DvdDTO dvd = ClientRun.controller.searchDvdById(copy.getMediumId());
                    lblTitle.setText(dvd.getTitle());
                    labelA.setText("Director: ");
                    label1.setText(dvd.getDirector());
                    labelB.setText("Publisher: ");
                    label2.setText(dvd.getPublisher());
                    labelC.setText("Release Date: ");
                    label3.setText(dvd.getReleaseDate().toString());
                    labelD.setText("ASIN: ");
                    label4.setText(dvd.getAsin());
                    labelE.setText("Copy Number: ");
                    label5.setText(String.valueOf(copy.getCopyNumber()));

                }
            } else {
                infoAlert("Could not find specified copy.");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        txtFieldCopyNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtFieldCopyNumber.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }
}
