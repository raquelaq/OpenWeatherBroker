package org.ulpgc.dacd.control;

public class Controller {
    public void execute() {
        Subscriber subscriber = new Subscriber();
        Thread thread = new Thread(subscriber);
        thread.start();
    }
}
