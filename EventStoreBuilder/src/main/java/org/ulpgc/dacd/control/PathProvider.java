package org.ulpgc.dacd.control;

public class PathProvider {
    public String provide() {
        return "EventStoreBuilder/src/main/resources/" + new DateProvider().provide() + ".events";
    }
}
