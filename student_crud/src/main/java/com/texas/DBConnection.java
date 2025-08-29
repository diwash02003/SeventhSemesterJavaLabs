package com.texas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created On:8/29/25 3:20 PM
 * Author: Sumit Kumar Shrestha
 * Description:
 **/
public class DBConnection {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/labjava";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "Geforce@2003";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
