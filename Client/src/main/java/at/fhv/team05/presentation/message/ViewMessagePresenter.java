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
            ResultDTO<MessageDTO> resultMessage = ClientRun.controller.receiveMessage();
            MessageDTO message = resultMessage.getDto();
            if (message != null && !message.getMessage().equalsIgnoreCase("NOMESSAGE")) {
                textOutputField.setText(message.getMessage());
            } else if (message != null && message.getMessage().equalsIgnoreCase("NOMESSAGE")){
                infoAlert(resultMessage.getException().getMessage());
            } else {
                errorAlert(resultMessage.getException().getMessage());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
