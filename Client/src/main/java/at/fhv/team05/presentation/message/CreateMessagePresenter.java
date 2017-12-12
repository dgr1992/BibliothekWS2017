package at.fhv.team05.presentation.message;

import at.fhv.team05.ClientRun;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.presentation.Presenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.rmi.RemoteException;


public class CreateMessagePresenter extends Presenter {

    @FXML
    private Button sendButton;

    @FXML
    private TextArea messageInputTextArea;

    @FXML
    private void sendMessage() {
        String messageText = messageInputTextArea.getText();
        if (messageText != null) {
            try {
                ResultDTO<Boolean> result = ClientRun.controller.sendMessage(messageText);
                if (result.getDto() != null) {
                    infoAlert(result.getException().getMessage());
                } else {
                    errorAlert(result.getException().getMessage());
                }
                messageInputTextArea.clear();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
