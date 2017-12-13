package at.fhv.team05.server.messaging;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Session;

public class ActiveMQCon {
    private static ActiveMQCon mInstance;
    private Destination destination;
    private Session session;
    private Connection connection;

    private ActiveMQCon(String brokerURL, String queueName) {
        try {
            //startBroker(brokerURL);


            //create the connection factory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);

            //create a connection
            connection = connectionFactory.createConnection();
            connection.start();

            //create a session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //create the queue
            destination = session.createQueue(queueName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void startBroker(String brokerURL) throws Exception {
        BrokerService broker = new BrokerService();
        // configure the broker
        broker.setBrokerName("localhost");
        broker.addConnector(brokerURL);
        broker.start();
    }

    public static ActiveMQCon getInstance() {
        if (mInstance == null) {
            mInstance = new ActiveMQCon(ActiveMQConnection.DEFAULT_BROKER_URL, "MessageQueue");
        }
        return mInstance;
    }

    public static ActiveMQCon createConnection(String brokerUrl, String queueName) {
        return mInstance = new ActiveMQCon(brokerUrl, queueName);
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
