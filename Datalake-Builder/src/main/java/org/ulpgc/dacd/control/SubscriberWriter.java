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

        Destination weatherDestination = session.createTopic("prediction.weather"); // Asegúrate de usar el nombre correcto del topic
        TopicSubscriber weatherDurableSubscriber = session.createDurableSubscriber((Topic) weatherDestination, "weatherSubscriber");
        subscribeAndWrite(weatherDurableSubscriber, "weather");

        Destination hotelDestination = session.createTopic("sensor.Hotel");
        TopicSubscriber hotelDurableSubscriber = session.createDurableSubscriber((Topic) hotelDestination, "hotelSubscriber");
        subscribeAndWrite(hotelDurableSubscriber, "hotel");
    }

    private Connection connect(String brokerUrl) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        return connectionFactory.createConnection("admin", "admin");
    }

    private Session createSession(Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    private void subscribeAndWrite(MessageConsumer consumer, String directoryType) throws JMSException {
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    String json = textMessage.getText();
                    System.out.println("Received message: " + json);
                    JsonObject eventJson = JsonParser.parseString(json).getAsJsonObject();
                    Instant systemTs = Instant.parse(eventJson.get("System_ts").getAsString());

                    String directoryPath;
                    if (directoryType.equals("weather")) {
                        directoryPath = new DirectoryCreator().createWeatherDirectory(systemTs);
                    } else {
                        directoryPath = new DirectoryCreator().createHotelDirectory(systemTs);
                    }

                    if (directoryPath != null) {
                        DateTimeFormatter fileNameFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                        String formattedTimestamp = fileNameFormatter.format(systemTs.atZone(ZoneId.systemDefault()));
                        String filePath = directoryPath + "/" + formattedTimestamp + ".events";
                        new FileAdministrator().write(filePath, json + "\n");
                    } else {
                        System.out.println("Error: Could not obtain the directory.");
                    }
                } catch (Exception e) {
                    System.err.println("Error processing message: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }

}

