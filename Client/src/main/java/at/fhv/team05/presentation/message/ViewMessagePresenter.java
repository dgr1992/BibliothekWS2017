package at.fhv.team05.presentation.message;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.dtos.MessageDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.rmi.RemoteException;

public class ViewMessagePresenter extends Presenter {

    @FXML
    private TextArea textOutputField;

    public void receiveMessage() {
        textOutputField.clear();
        try {
            ResultDTO<MessageDTO> text = ClientRun.controller.receiveMessage();
            if (text.getDto() != null && !text.getDto().getMessage().equalsIgnoreCase("ERROR")) {
                textOutputField.setText(text.getDto().getMessage());
            } else {
                infoAlert(text.getException().getMessage());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
