package com.revature.db;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// factory-class
public class MySQLConnectionFactory {

    // factory-method
    public static Connection getConnection() throws SQLException {

        Connection connection = null;
        // step-1 : Install / Register JDBC driver
//        DriverManager.registerDriver(new Driver());

        // step-2 : create DB-connection with URL, username & password
        String url = "jdbc:mysql://localhost:3306/shopdb";
        String username = "root";
        String password = "admin";

        connection = DriverManager.getConnection(url, username, password);
//      System.out.println("java application connected to mysql-server");

        return connection;

    }
}

