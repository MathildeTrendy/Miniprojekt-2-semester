package com.example.MiniProject.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ManagerDb {

    private static Connection connection = null;

    public static Connection getConnection() {

        String username = null;
        String email = null;
        String password = null;

        try(InputStream input = new FileInputStream("src/main/resources/application.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            username = properties.getProperty("username");
            password = properties.getProperty("password");
            email = properties.getProperty("email");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
        connection = DriverManager.getConnection(username,password,email);
    } catch(SQLException e){
        e.printStackTrace();
    }
        return connection;
}
}
