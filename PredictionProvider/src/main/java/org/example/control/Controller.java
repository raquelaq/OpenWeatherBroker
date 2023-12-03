package org.example.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;

public class Controller {
    public void execute() throws SQLException {

        MyTimerTask myTimerTask = new MyTimerTask();
        Timer timer = new Timer();
        timer.schedule(myTimerTask, 0, 6 * 60 * 60 * 1000);
    }
}
