package org.example.control;

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
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    System.out.println("Received message: " + textMessage.getText());
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void main(String[] args) {
        Subscriber subscriber = new Subscriber();
        Thread thread = new Thread(subscriber);
        thread.start();
    }
}
