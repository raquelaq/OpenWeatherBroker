package org.ulpgc.dacd.control;

public class PathProvider {
    public String provide() {
        DirectoryCreator directoryCreator = new DirectoryCreator();
        String directoryPath = directoryCreator.createDirectory();
        return directoryPath + "/" + new DateProvider().provide() + ".events";
    }
}
