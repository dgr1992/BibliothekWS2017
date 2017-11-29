package at.fhv.team05.messaging;

import javax.jms.*;

public class Producer {

    private static Producer mInstance;
    private final Session _session;
    private MessageProducer _producer;

    private Producer() {
        ActiveMQCon activeMQCon = ActiveMQCon.getInstance();
        _session = activeMQCon.getSession();
        Destination destination = activeMQCon.getDestination();
        try {
            _producer = _session.createProducer(destination);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static Producer getInstance() {
        if (mInstance == null) {
            mInstance = new Producer();
        }
        return mInstance;
    }

    public void sendMessage(String text) throws JMSException {
        _producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // Create a messages
        TextMessage message = _session.createTextMessage(text);

        // Tell the producer to send the message
        System.out.println("Sent message: " + message.getText());
        _producer.send(message);
    }
}
