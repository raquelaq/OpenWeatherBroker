package org.ulpgc.dacd.control;

public class Controller {
    public void execute() {
        SubscriberWriter subscriberWriter = new SubscriberWriter();
        Thread thread = new Thread(subscriberWriter);
        thread.start();
    }
}
