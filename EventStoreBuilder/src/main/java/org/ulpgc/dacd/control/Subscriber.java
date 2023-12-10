package org.ulpgc.dacd.control;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Subscriber implements Runnable {
    @Override
    public void run() {
        try {
            start();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private void start() throws JMSException {
        Connection connection = connect("tcp://localhost:61616");
        connection.start();
        Session session = createSession(connection);
        Destination destination = session.createTopic("prediction.Weather");
        MessageConsumer consumer = session.createConsumer(destination);
        subscribe(consumer);
    }

    private Connection connect(String brokerUrl) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        return connectionFactory.createConnection("admin", "admin");
    }

    private Session createSession(Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    private void subscribe(MessageConsumer consumer) throws JMSException {
        FileAdministrator fileAdministrator = new FileAdministrator();

        String directoryPath = new PathProvider().provide();
        if (directoryPath != null) {
            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage textMessage) {
                    try {
                        System.out.println("Received message: " + textMessage.getText());
                        fileAdministrator.write(directoryPath, textMessage.getText() + "\n");
                    } catch (JMSException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
            System.out.println("Error: Could not obtain the directoy.");
        }
    }
}

