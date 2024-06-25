package org.example;

import java.sql.*;

public class DatabaseManager {
    private static final String path = "src\\main\\resources\\";

    public static String getPath() {
        return path;
    }

    public static Connection getConnection(String fileName) throws SQLException {
        String url = "jdbc:sqlite:" + path + fileName;
        System.out.println("Connecting to DB at: " + url);
        return DriverManager.getConnection(url);
    }
}
