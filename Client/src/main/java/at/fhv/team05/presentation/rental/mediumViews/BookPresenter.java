package at.fhv.team05.presentation.rental.mediumViews;

import at.fhv.team05.dtos.CopyDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Michelle on 05.11.2017.
 */
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
        //TODO get medium
//        lblTitle.setText(bookDto.getTitle());
//        lblAuthor.setText(bookDto.getAuthor());
//        lblPublisher.setText(bookDto.getPublisher());
//        lblReleaseDate.setText(bookDto.getReleaseDate().toString());
//        lblIsbn.setText(bookDto.getIsbn());
    }
}
