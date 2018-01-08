package at.fhv.team05.client.presentation;
import at.fhv.team05.client.presentation.mainView.MainViewPresenter;
import javafx.scene.control.Alert;

public abstract class Presenter {
    protected MainViewPresenter parent;

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

    public void setParent(MainViewPresenter parent) {
        this.parent = parent;
    }
}
