package at.fhv.team05.messaging;

import javax.jms.*;

public class Consumer {

    private static Consumer mInstance;
    private final ActiveMQCon _activeMQCon;
    private MessageConsumer _consumer;

    private Consumer() {
        _activeMQCon = ActiveMQCon.getInstance();
        Session session = _activeMQCon.getSession();
        Destination destination = _activeMQCon.getDestination();
        try {
            _consumer = session.createConsumer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static Consumer getInstance() {
        if (mInstance == null) {
            mInstance = new Consumer();
        }
        return mInstance;
    }

    public String receiveMessage() throws Exception {
        _activeMQCon.getConnection().start();

        // Wait for a message
        Message message = _consumer.receive(1000);
        String messageText;
        if (message != null) {
            TextMessage textMessage = (TextMessage) message;
            messageText = textMessage.getText();
        } else {
            throw new Exception("No messages available.");
        }
        System.out.println("Received: " + messageText);
        return messageText;
    }
}
