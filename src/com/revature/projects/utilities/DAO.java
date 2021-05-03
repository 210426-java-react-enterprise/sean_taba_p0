package com.revature.projects.utilities;

import java.sql.*;

public class DAO {

    private final Connection connection;

    public DAO() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/p0", "root", "seantaba");
    }

    public boolean tryNewCredentials(MyList<String> list) throws SQLException
    {
        Statement statement = connection.createStatement();
        String query = "select id,username,password from credentials where username = '" + list.at(0) + "'";

        ResultSet result = statement.executeQuery(query);
        if(!result.next())
        {
//            query = "insert into credentials (username, password) VALUES ('" + list.at(0) + "','" + list.at(1) + "');";
//            statement.execute(query);
//            query = "select id from credentials where username = '" + list.at(0) + "'";
//            ResultSet resultSet = statement.executeQuery(query);
//            if (resultSet.next())
//                list.add(String.valueOf(resultSet.getInt("id")));
            return false;
        } else return true;
    }

}
