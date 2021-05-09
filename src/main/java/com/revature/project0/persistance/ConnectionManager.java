package com.revature.project0.persistance;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static ConnectionManager instance;
    private final Properties properties;

    private ConnectionManager()
    {
        properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance()
    {
        if (instance == null)
        {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(
                    properties.getProperty("host-url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
                    );
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}
