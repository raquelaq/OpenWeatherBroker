package org.example;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
        processMessages(consumer);
    }

    private Connection connect(String brokerUrl) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        return connectionFactory.createConnection("admin", "admin");
    }

    private Session createSession(Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    private void processMessages(MessageConsumer consumer) throws JMSException {
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    String json = textMessage.getText();
                    JsonObject eventJson = JsonParser.parseString(json).getAsJsonObject();
                    SqliteDataMart dataMart = new SqliteDataMart();
                    dataMart.updateWeatherData(eventJson);
                    dataMart.insertWeatherData(eventJson);
                } catch (Exception e) {
                    System.err.println("Error processing message: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });

    }
}
