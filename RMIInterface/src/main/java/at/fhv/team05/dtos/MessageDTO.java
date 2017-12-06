package at.fhv.team05.dtos;

import at.fhv.team05.ObjectInterfaces.IMessage;

import java.io.Serializable;

public class MessageDTO implements Serializable{
    private String message;

    public MessageDTO(IMessage message) {
        this.message = message.getMessage();
    }

    public MessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
