package at.fhv.team05.messaging;

import javax.jms.*;

public class Consumer {

    private static Consumer mInstance;
    private final ActiveMQCon _activeMQCon;

    private Consumer() {
        _activeMQCon = ActiveMQCon.getInstance();
    }

    public static Consumer getInstance() {
        if (mInstance == null) {
            mInstance = new Consumer();
        }
        return mInstance;
    }

    public String receiveMessage() throws JMSException {
        Session session = _activeMQCon.getSession();
        Destination destination = _activeMQCon.getDestination();
        MessageConsumer consumer = session.createConsumer(destination);

        // Wait for a message
        Message message = consumer.receive(5000);
        String messageText = "";
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            messageText = textMessage.getText();
        } else {
            messageText = message.toString();
        }
        System.out.println("Received: " + messageText);
        return messageText;
    }
}
