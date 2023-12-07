package org.ulpgc.dacd.control;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.io.File;
import java.util.List;

public class MessageStorer {
    private static void storeMessageInEventStore(TextMessage message) throws JMSException {
        FileAdministrator fileAdministrator = new FileAdministrator();
        final String baseDirectory = "EventStoreBuilder/prediction.Weather/";
        String ss = "prediction-provider";
        String directoryPath = baseDirectory + ss + "/";
        String fileName = directoryPath + getCurrentDate() + ".events";
        String messageText = message.getText();
        File eventStoreDirectory = new File(directoryPath);

        if (!eventStoreDirectory.exists()) {
            eventStoreDirectory.mkdirs();
        }

        fileAdministrator.write(fileName, messageText);
    }

    private static String getCurrentDate() {
        return java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE);
    }


}
