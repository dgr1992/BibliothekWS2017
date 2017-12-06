package at.fhv.team05.client.presentation.rental;

import at.fhv.team05.client.ClientRun;
import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.library.dtos.BookDTO;
import at.fhv.team05.library.dtos.CopyDTO;
import at.fhv.team05.library.dtos.DvdDTO;
import at.fhv.team05.client.presentation.Presenter;
import at.fhv.team05.client.presentation.customer.buttons.CustomerButtonType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

import java.rmi.RemoteException;

public class RentalPresenter extends Presenter {
    private CopyDTO copy;

    @FXML
    private TitledPane titledPaneMediumInfo;

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

    /**
     * Method for opening the customer view after choosing the copy which should be rented.
     */
    @FXML
    void onOKButtonPressed(ActionEvent event) {
        if (copy != null) {
            //Check if copy is already rented or not.
            if (copy.getRental() == null) {
                parent.openCustomerView(copy, null, CustomerButtonType.OK);
            } else {
                infoAlert("This Medium is already rented");
            }
        } else {
            infoAlert("Please enter a Medium");
        }
    }

    /**
     * Searched the copy with the copy number which is typed in the text field.
     */
    @FXML
    void onSearchButtonPressed(ActionEvent event) {
        if (!txtFieldCopyNumber.getText().isEmpty()) {
            int copyNumber = Integer.valueOf(txtFieldCopyNumber.getText());
            try {

                //Searches the copy for the given copy number.
                ResultDTO<CopyDTO> resultCopy = ClientRun.controller.searchCopyByCopyNumber(copyNumber);

                //Checks if the copy with this copy number exists.
                if (resultCopy != null) {
                    copy = resultCopy.getDto();

                    //Checks if the copy was successfully loaded.
                    if (resultCopy.getException() == null) {

                        //Display information for book-copy
                        if ("book".equalsIgnoreCase(copy.getMediaType())) {
                            BookDTO book = ClientRun.controller.searchBookById(copy.getMediumId()).getDto();
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

                            // Display information for dvd-copy
                        } else if ("dvd".equalsIgnoreCase(copy.getMediaType())) {
                            DvdDTO dvd = ClientRun.controller.searchDvdById(copy.getMediumId()).getDto();
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
                        errorAlert(resultCopy.getException().getMessage());
                    }
                } else {
                    errorAlert("Could not find specified copy.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            infoAlert("Please type in a copy number.");
        }

    }

    public void initialize() {
        titledPaneMediumInfo.setCollapsible(false);
        //Regular Expression for restricting input to numbers.
        txtFieldCopyNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtFieldCopyNumber.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
