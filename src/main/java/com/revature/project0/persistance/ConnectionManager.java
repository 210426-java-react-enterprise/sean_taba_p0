package com.revature.project0.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private final Properties properties;

    public ConnectionManager(Properties properties)
    {
        this.properties = properties;
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
