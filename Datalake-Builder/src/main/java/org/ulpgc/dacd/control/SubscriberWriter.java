package org.ulpgc.dacd.control;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.jms.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class SubscriberWriter implements Runnable {
    private static final String DURABLE_SUBSCRIBER_ID = "admin";
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
        connection.setClientID(DURABLE_SUBSCRIBER_ID);
        connection.start();
        Session session = createSession(connection);
        Destination destination = session.createTopic("prediction.Weather");

        TopicSubscriber durableSubscriber = session.createDurableSubscriber((Topic) destination, DURABLE_SUBSCRIBER_ID);
        subscribeAndWrite(durableSubscriber);
    }

    private Connection connect(String brokerUrl) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        return connectionFactory.createConnection("admin", "admin");
    }

    private Session createSession(Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    private void subscribeAndWrite(MessageConsumer consumer) throws JMSException {
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    String json = textMessage.getText();
                    System.out.println("Received message: " + json);

                    JsonObject eventJson = JsonParser.parseString(json).getAsJsonObject();
                    try {
                        Instant systemTs = Instant.parse(eventJson.get("System_ts").getAsString());
                        System.out.println("Extracted date: " + systemTs);

                        DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                        String formattedTimestamp = fileNameFormatter.format(systemTs.atZone(ZoneId.systemDefault()));

                        String directoryPath = new DirectoryCreator().createDirectory(systemTs);
                        if (directoryPath != null) {
                            String filePath = directoryPath + "/" + formattedTimestamp + ".events";
                            new FileAdministrator().write(filePath, json + "\n");
                        } else {
                            System.out.println("Error: Could not obtain the directory.");
                        }
                    } catch (Exception e) {
                        System.err.println("Error parsing date: " + e.getMessage());
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    System.err.println("Error processing message: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}

