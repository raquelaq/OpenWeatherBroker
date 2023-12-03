package org.example.control;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.model.Weather;

public class MessageSender {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; //DEFAULT_BROKER significa que el servidor JMS esta en localhost
    private static String topic = "prediction.Weather";

    public static void main(String[] args) throws JMSException{
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createTopic(topic);

        MessageProducer producer = session.createProducer(destination);

        WeatherStore weatherStore = new WeatherStore();

        try {
            for (Weather weather : weatherStore.getWeatherData()) {
                String jsonWeatherEvent = weather.buildJson();
                TextMessage message = session.createTextMessage(jsonWeatherEvent);
                producer.send(message);
                System.out.println("Sent weather event to topic: " + topic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
