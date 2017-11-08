package at.fhv.team05.presentation.mediumViews;

import at.fhv.team05.ClientRun;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.DvdDTO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class DvdPresenter implements Initializable{
    private CopyDTO copy;
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

    public void setCopy(CopyDTO copy) {
        this.copy = copy;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            DvdDTO dvd = ClientRun.controller.searchDvdById(copy.getMediumId());
            lblTitle.setText(dvd.getTitle());
            lblDirector.setText(dvd.getDirector());
            lblPublisher.setText(dvd.getPublisher());
            lblReleaseDate.setText(dvd.getReleaseDate().toString());
            lblAsin.setText(dvd.getAsin());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
