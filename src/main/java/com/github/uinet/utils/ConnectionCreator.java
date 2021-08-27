package com.github.uinet.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/restaurant_servlet_db?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASS = "password";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private ConnectionCreator(){
    }

    public static Connection createConnection(){
        try {
            return DriverManager.getConnection(
                    DATABASE_URL,
                    DATABASE_USER,
                    DATABASE_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
