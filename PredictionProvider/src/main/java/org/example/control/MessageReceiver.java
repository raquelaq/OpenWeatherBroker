package org.example.control;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageReceiver {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String topic = "prediction.Weather";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic(topic);

        MessageConsumer consumer = session.createConsumer(destination);

        consumer.setMessageListener(new WeatherMessageListener());

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        connection.close();
    }
}

class WeatherMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                System.out.println("Received weather event: " + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}