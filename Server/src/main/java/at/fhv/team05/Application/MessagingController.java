package at.fhv.team05.Application;

import at.fhv.team05.Application.medium.BookController;
import at.fhv.team05.Application.medium.DvdController;
import at.fhv.team05.ObjectInterfaces.ICustomer;
import at.fhv.team05.ResultDTO;
import at.fhv.team05.ResultListDTO;
import at.fhv.team05.domain.Copy;
import at.fhv.team05.domain.Rental;
import at.fhv.team05.domain.medium.Medium;
import at.fhv.team05.dtos.*;
import at.fhv.team05.messaging.Consumer;
import at.fhv.team05.messaging.Producer;

import javax.jms.JMSException;
import java.util.Date;
import java.util.List;


public class MessagingController {
    private static MessagingController _theInstance;
    private final Producer _producer;
    private final Consumer _consumer;

    private MessagingController() {
        _producer = Producer.getInstance();
        _consumer = Consumer.getInstance();
    }

    public static MessagingController getInstance() {
        if (_theInstance == null) {
            _theInstance = new MessagingController();
        }
        return _theInstance;
    }

    public void createReturnedCopyMessage(CopyDTO copyDTO) {
        try {
            IMediumDTO medium;
            if ("book".equalsIgnoreCase(copyDTO.getMediaType())) {
                BookController bookController = BookController.getInstance();
                ResultDTO<BookDTO> resultDTO = bookController.searchById(copyDTO.getMediumId());
                medium = resultDTO.getDto();
            } else {
                DvdController dvdController = DvdController.getInstance();
                ResultDTO<DvdDTO> resultDTO = dvdController.searchById(copyDTO.getMediumId());
                medium = resultDTO.getDto();
            }
            ReservationController reservationController = ReservationController.getInstance();
            ResultListDTO<ReservationDTO> reservationResult = reservationController.getReservationsByMedium(medium);
            List<ReservationDTO> reservations = reservationResult.getListDTO();
            Date reservationDate = reservations.get(0).getReservationDate();
            int index = 0;
            for (int i = 1; i < reservations.size(); i++) {
                Date tmpDate = reservations.get(i).getReservationDate();
                if (tmpDate.before(reservationDate)) {
                    reservationDate = tmpDate;
                    index = i;
                }
            }
            CustomerDTO customer = reservations.get(index).getCustomer();

            _producer.sendMessage("The returned copy " + medium.getTitle() + " (" + medium.getType() + ") " +
                    "has been returned and is reserved by " + customer.getFirstName() + " " + customer.getLastName() +
                    " (Customer Number: " + customer.getCustomerId() + ")");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void createRequestForPaymentMessage(Rental rental) {
        try {
            Copy copy = (Copy) rental.getCopy();
            ICustomer customer = rental.getCustomer();
            Medium medium;
            if ("book".equalsIgnoreCase(copy.getMediaType())) {
                BookController bookController = BookController.getInstance();
                medium = bookController.getDomain(bookController.searchById(copy.getMediumId()).getDto());
            } else {
                DvdController dvdController = DvdController.getInstance();
                medium = dvdController.getDomain(dvdController.searchById(copy.getMediumId()).getDto());
            }
            _producer.sendMessage(customer.getFirstName() + " " + customer.getLastName() + "(Customer Numer: " +
                    customer.getCustomerId() + ")" + " has not returned his copy of " + medium.getTitle() +
                    "(" + medium.getType() + ").\nThe rental expired on " + rental.getDeadline());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public ResultDTO<Boolean> sendMessage(String messageText) {
        try {
            _producer.sendMessage(messageText);
            return new ResultDTO<>(true, new Exception("Message successfully sent."));
        } catch (JMSException e) {
            return new ResultDTO<>(false, new Exception("Message could not be sent."));
        }
    }

    public ResultDTO<String> receiveMessage() {
        try {
            return new ResultDTO<>(_consumer.receiveMessage(), new Exception("Message successfully loaded"));
        } catch (JMSException e) {
            return new ResultDTO<>("No message available", new Exception("Could not load message"));
        }
    }
}
