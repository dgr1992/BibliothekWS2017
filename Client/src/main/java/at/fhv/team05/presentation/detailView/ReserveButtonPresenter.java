package at.fhv.team05.presentation.detailView;

import at.fhv.team05.dtos.IMediumDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReserveButtonPresenter {
    private IMediumDTO medium;

    @FXML
    void onReserveMediumButtonPressed(ActionEvent event) {

    }

    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }
}
