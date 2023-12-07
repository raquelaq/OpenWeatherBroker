package org.ulpgc.dacd.control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateProvider {
    public String provide() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuuMMdd");
        LocalDate localDate = LocalDate.now();
        return dateTimeFormatter.format(localDate);
    }
}
