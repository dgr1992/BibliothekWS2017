package at.fhv.team05.messaging;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

public class ActiveMQCon {
    private static ActiveMQCon mInstance;
    private Destination destination;
    private Session session;
    private Connection connection;

    private ActiveMQCon(String brokerURL, String queueName) {
        try {
            //create the connection factory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

            //create a connection
            connection = connectionFactory.createConnection();
            connection.start();

            //create a session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //create the queue
            destination = session.createQueue(queueName);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public static ActiveMQCon getInstance() {
        if (mInstance == null) {
            mInstance = new ActiveMQCon(ActiveMQConnection.DEFAULT_BROKER_URL, "MessageQueue");
        }
        return mInstance;
    }

    public Destination getDestination() {
        return destination;
    }

    public Session getSession() {
        return session;
    }

    public Connection getConnection() {
        return connection;
    }
}
