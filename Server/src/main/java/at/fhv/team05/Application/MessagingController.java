package at.fhv.team05.Application;

import at.fhv.team05.Application.medium.BookController;
import at.fhv.team05.Application.medium.DvdController;
import at.fhv.team05.dtos.CopyDTO;
import at.fhv.team05.dtos.IMediumDTO;
import at.fhv.team05.messaging.Producer;

import javax.jms.JMSException;

public class MessagingController {
    private static MessagingController _theInstance;

    private MessagingController() {

    }

    public static MessagingController getInstance() {
        if (_theInstance == null) {
            _theInstance = new MessagingController();
        }
        return _theInstance;
    }

    public void createReturnedCopyMessage(CopyDTO copyDTO) {
        try {
            Producer producer = Producer.getInstance();
            IMediumDTO medium;
            if ("book".equalsIgnoreCase(copyDTO.getMediaType())) {
                BookController bookController = BookController.getInstance();
                medium = (IMediumDTO) bookController.searchById(copyDTO.getMediumId());
            } else {
                DvdController dvdController = DvdController.getInstance();
                medium = (IMediumDTO) dvdController.searchById(copyDTO.getMediumId());
            }

            producer.sendMessage("The returned copy " + medium.getTitle() + " (" + medium.getType() + ") " + "has been returned.");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
