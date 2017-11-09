package at.fhv.team05.presentation.detailView;

import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReserveButtonPresenter extends Presenter{
    private IMediumDTO medium;

    @FXML
    void onReserveMediumButtonPressed(ActionEvent event) {

    }

    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }
}
