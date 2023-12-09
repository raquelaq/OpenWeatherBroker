package org.ulpgc.dacd.control;

import java.util.Timer;

public class Controller {
    public void execute(){
        MyTimerTask myTimerTask = new MyTimerTask();
        Timer timer = new Timer();
        timer.schedule(myTimerTask, 0, 6 * 60 * 60 * 1000);
    }
}
