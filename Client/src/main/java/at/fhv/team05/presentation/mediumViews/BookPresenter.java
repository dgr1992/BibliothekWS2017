package at.fhv.team05.presentation.mediumViews;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.BookDTO;
import at.fhv.team05.dtos.CopyDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class BookPresenter implements Initializable {
    private CopyDTO copy;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblPublisher;

    @FXML
    private Label lblReleaseDate;

    @FXML
    private Label lblIsbn;

    public void setCopy(CopyDTO copy) {
        this.copy = copy;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            BookDTO book = ClientRun.controller.searchBookById(copy.getMediumId());
            lblTitle.setText(book.getTitle());
            lblAuthor.setText(book.getAuthor());
            lblPublisher.setText(book.getPublisher());
            lblReleaseDate.setText(book.getReleaseDate().toString());
            lblIsbn.setText(book.getIsbn());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
