package org.ulpgc.dacd.control;

import java.util.Timer;

public class Controller {
    public void execute(String apiKey){
        MyTimerTask myTimerTask = new MyTimerTask(apiKey);
        Timer timer = new Timer();
        timer.schedule(myTimerTask, 0, 6 * 60 * 60 * 1000);
    }
}
