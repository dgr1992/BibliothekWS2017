package at.fhv.team05.presentation.rental;

import at.fhv.team05.presentation.mainView.MainViewPresenter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class RentalPresenter {
    MainViewPresenter parent;

    @FXML
    private TextField txtFieldMediumNumber;

    @FXML
    private StackPane mediumContainer;

    @FXML
    void onNextButtonPressed(ActionEvent event) {
        parent.openCustomerRentalView();
    }


    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
    }
}
