package at.fhv.team05.presentation.rental.mediumViews;

import at.fhv.team05.dtos.DvdDTO;
import at.fhv.team05.dtos.IMediumDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DvdPresenter implements Initializable{
    private DvdDTO dvdDto;
    @FXML
    private Label lblTitle;

    @FXML
    private Label lblDirector;

    @FXML
    private Label lblPublisher;

    @FXML
    private Label lblReleaseDate;

    @FXML
    private Label lblAsin;

    public void setDvdDto(IMediumDTO dvdDto) {
        this.dvdDto = (DvdDTO) dvdDto;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitle.setText(dvdDto.getTitle());
        lblDirector.setText(dvdDto.getDirector());
        lblPublisher.setText(dvdDto.getPublisher());
        lblReleaseDate.setText(dvdDto.getReleaseDate().toString());
        lblAsin.setText(dvdDto.getAsin());
    }
}
