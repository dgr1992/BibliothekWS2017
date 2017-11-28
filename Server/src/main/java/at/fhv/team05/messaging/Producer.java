package at.fhv.team05.messaging;

import javax.jms.*;

public class Producer {

    private static Producer mInstance;
    private final ActiveMQCon _activeMQCon;

    private Producer() {
        _activeMQCon = ActiveMQCon.getInstance();
    }

    public static Producer getInstance() {
        if (mInstance == null) {
            mInstance = new Producer();
        }
        return mInstance;
    }

    public void sendMessage(String text) throws JMSException {
        Session session = _activeMQCon.getSession();
        Destination destination = _activeMQCon.getDestination();

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Create a messages
        TextMessage message = session.createTextMessage(text);

        // Tell the producer to send the message
        System.out.println("Sent message: " + message.getText());
        producer.send(message);
    }
}
