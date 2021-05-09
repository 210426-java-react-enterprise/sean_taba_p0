package com.revature.project0.persistance;

import com.revature.project0.models.Customer;
import com.revature.project0.utilities.MyList;

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
        String query = "select user_name from project0.credentials where user_name = '" + username + "'";

        ResultSet result = statement.executeQuery(query);
        return result.next();
    }
    public boolean tryNewSSN(String ssn) throws SQLException
    {
        Statement statement = connection.createStatement();
        String query = "select ssn from project0.customers where ssn = '" + ssn + "'";

        ResultSet result = statement.executeQuery(query);
        return result.next();
    }

    public void addCustomer(Customer customer) throws SQLException
    {
        String sqlInsertNewCustomer = "insert into project0.customers (first_name,last_name,ssn,email,phone) values (?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertNewCustomer);
        preparedStatement.setString(1, customer.getFirstName());
        preparedStatement.setString(2, customer.getLastName());
        preparedStatement.setString(3, customer.getSsn());
        preparedStatement.setString(4, customer.getEmail());
        preparedStatement.setString(5, customer.getPhone());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected == 0) System.out.println("Something went wrong!");

//        int customerID = 0;
//
//        if(rowsAffected != 0)
//        {
//            ResultSet resultSet = preparedStatement.getGeneratedKeys();
//            if(resultSet.next())
//            customerID = resultSet.getInt("id");
//        } else System.out.println("Something went wrong!");

        sqlInsertNewCustomer = "insert into project0.credentials (user_name,password,customer_ssn) values (?,?,?);";
        preparedStatement = connection.prepareStatement(sqlInsertNewCustomer);
        preparedStatement.setString(1, customer.getUsername());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, String.valueOf(customer.getSsn()));

        rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected == 0) System.out.println("Something went wrong!");

        sqlInsertNewCustomer = "insert into project0.addresses (unit,street,city,state,zip,customer_ssn) values (?,?,?,?,?,?);";
        preparedStatement = connection.prepareStatement(sqlInsertNewCustomer);
        preparedStatement.setString(1, customer.getUnit());
        preparedStatement.setString(2, customer.getStreet());
        preparedStatement.setString(3, customer.getCity());
        preparedStatement.setString(4, customer.getState());
        preparedStatement.setString(5, customer.getZip());
        preparedStatement.setString(6, String.valueOf(customer.getSsn()));

        rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected == 0) System.out.println("Something went wrong!");

    }

    public String isCorrectPassword(String username) throws SQLException
    {
        String query = "select * from project0.credentials where user_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
        {
            return resultSet.getString("password");
        }
        return null;
    }

    public MyList<String> getAccounts(String username) throws SQLException
    {
        String query = "select account from project0.accounts where user_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        MyList<String> accounts = new MyList<>();
        while(resultSet.next())
        {
            accounts.add(resultSet.getString("account"));
        }
        return accounts;
    }
}
