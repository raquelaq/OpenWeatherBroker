package org.example.control;

import java.util.Timer;

public class TripAdvisorController {
    public void execute(){
        MyTimerTask myTimerTask = new MyTimerTask();
        Timer timer = new Timer();
        timer.schedule(myTimerTask, 0, 6 * 60 * 60 * 1000);
    }
}
