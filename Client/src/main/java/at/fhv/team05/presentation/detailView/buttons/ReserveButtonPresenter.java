package at.fhv.team05.presentation.detailView.buttons;

import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.presentation.Presenter;
import at.fhv.team05.presentation.customer.buttons.CustomerButtonType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ReserveButtonPresenter extends Presenter{
    private IMediumDTO medium;

    @FXML
    void onReserveMediumButtonPressed(ActionEvent event) {
        parent.openCustomerView(null, medium, CustomerButtonType.RESERVATION);
    }

    public void setMedium(IMediumDTO medium) {
        this.medium = medium;
    }
}
