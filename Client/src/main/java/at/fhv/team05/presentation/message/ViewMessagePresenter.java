package at.fhv.team05.presentation.message;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.rmi.RemoteException;

public class ViewMessagePresenter extends Presenter{

    @FXML
    private TextArea textOutputField;

    public void receiveMessage() {
        textOutputField.clear();
        try {
            ResultDTO<String> text = ClientRun.controller.receiveMessage();
            textOutputField.setText(text.getDto());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
