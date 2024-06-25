package org.example.control;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DatabaseOperationsManager {
    public void createDatabase(String fileName) {
        String url = "jdbc:sqlite:" + DatabaseManager.getPath() + fileName;
        try (Connection conn = DatabaseManager.getConnection(fileName)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
