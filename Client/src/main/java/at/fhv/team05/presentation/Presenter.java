package at.fhv.team05.presentation;

import javafx.scene.control.Alert;

/**
 * Created by Michelle on 09.11.2017.
 */
public abstract class Presenter {

    protected void infoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(message);
        alert.show();
    }

    protected void errorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(message);
        alert.show();
    }
}
