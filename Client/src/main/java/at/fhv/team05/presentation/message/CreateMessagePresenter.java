package at.fhv.team05.presentation.message;

import at.fhv.team05.presentation.Presenter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class CreateMessagePresenter extends Presenter {

    @FXML
    private Button sendButton;

    @FXML
    private TextArea messageInputTextArea;

    private void sendMessage() {

        String messageText = messageInputTextArea.getText();
        if (messageText != null) {

        }
    }

}
