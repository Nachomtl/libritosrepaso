package org.example;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
//para practicar
    public class DatabaseConnection {
        private static final String URL = "jdbc:mariadb://localhost:3306/biblioteca";
        private static final String USER = "nacho";
        private static final String PASSWORD = "1234";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }


