package com.revature.project0.utilities;

import java.sql.*;

public class DAO {

    private final Connection connection;
    private static DAO instance;

    private DAO() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.connection = ConnectionManager.getInstance().getConnection();
    }

    public static DAO getInstance() throws SQLException, ClassNotFoundException
    {
        if (instance == null)
        {
            instance = new DAO();
        }
        return  instance;
    }

    public boolean tryNewUsername(String username) throws SQLException
    {
        Statement statement = connection.createStatement();
        String query = "select username from project0.credentials where username = '" + username + "'";

        ResultSet result = statement.executeQuery(query);
        return !result.next();
    }

}
