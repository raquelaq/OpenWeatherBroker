package org.example.control;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class SensorPublisher implements Runnable{
    private final String apiKey;

    public SensorPublisher(String apiKey) {
        this.apiKey = apiKey;
    }

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
        MessageProducer producer = session.createProducer(destination);
        //sendFlightEvents(session, producer, apiKey);
        disconnect(producer, session, connection);
    }

    private Connection connect(String brokerUrl) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        return connectionFactory.createConnection("admin", "admin");
    }

    private Session createSession(Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    /*private void sendWeatherEvents(Session session, MessageProducer producer, String apiKey) throws JMSException {
        WeatherManager weatherManager = new WeatherManager(apiKey);

        try {
            for (Weather weather : weatherManager.getWeatherData()) {
                String jsonWeatherEvent = weather.buildJson();
                TextMessage message = session.createTextMessage(jsonWeatherEvent);
                producer.send(message);
                System.out.println("Sent weather event: " + jsonWeatherEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void disconnect(MessageProducer producer, Session session, Connection connection) throws JMSException {
        producer.close();
        session.close();
        connection.close();
    }
}
