package org.example.control;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.model.Hotel;

import javax.jms.*;
import java.time.Instant;

public class HotelDataPublisher implements Runnable{
    @Override
    public void run() {
        try {
            start();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

    private void start() throws JMSException {
        System.out.println("Connecting to broker...");
        Connection connection = connect("tcp://localhost:61616");
        connection.start();
        System.out.println("Connection started, creating session...");
        Session session = createSession(connection);
        Destination destination = session.createTopic("sensor.Hotel");
        MessageProducer producer = session.createProducer(destination);
        System.out.println("Session and producer created, sending events...");
        sendHotelEvents(session, producer);
        System.out.println("Events sent, disconnecting...");
        disconnect(producer, session, connection);
        System.out.println("Disconnected.");
    }

    private Connection connect(String brokerUrl) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        return connectionFactory.createConnection("admin", "admin");
    }

    private Session createSession(Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    private void sendHotelEvents(Session session, MessageProducer producer) throws JMSException {
        HotelManager hotelManager = new HotelManager();
        try {
            for (Hotel hotel : hotelManager.getHotelData()) {
                Instant now = Instant.now();
                String jsonHotelEvent = hotel.buildJson(now);
                TextMessage message = session.createTextMessage(jsonHotelEvent);
                producer.send(message);
                System.out.println("Sent hotel event: " + jsonHotelEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void disconnect(MessageProducer producer, Session session, Connection connection) throws JMSException {
        producer.close();
        session.close();
        connection.close();
    }
}
