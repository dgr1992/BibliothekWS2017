package at.fhv.team05.client.presentation.message;

import at.fhv.team05.client.ClientRun;
import at.fhv.team05.library.ResultDTO;
import at.fhv.team05.library.dtos.MessageDTO;
import at.fhv.team05.client.presentation.Presenter;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
