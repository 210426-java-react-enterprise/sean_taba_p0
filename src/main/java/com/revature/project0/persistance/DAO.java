package com.revature.project0.persistance;

import com.revature.project0.models.*;
import com.revature.project0.utilities.CurrentCustomer;
import com.revature.project0.utilities.MyList;

import javax.accessibility.AccessibleAction;
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

        sqlInsertNewCustomer = "insert into project0.credentials (user_name,password,customer_ssn) values (?,?,?);";
        preparedStatement = connection.prepareStatement(sqlInsertNewCustomer);
        preparedStatement.setString(1, customer.getUsername());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, String.valueOf(customer.getSsn()));

        rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected == 0) System.out.println("Something went wrong!");

        sqlInsertNewCustomer = "insert into project0.addresses (unit,street,city,\"state\",zip,customer_ssn) values (?,?,?,?,?,?)";
        preparedStatement = connection.prepareStatement(sqlInsertNewCustomer);
        preparedStatement.setString(1, customer.getUnit());
        preparedStatement.setString(2, customer.getStreet());
        preparedStatement.setString(3, customer.getCity());
        preparedStatement.setString(4, customer.getState());
        preparedStatement.setString(5, customer.getZip());
        preparedStatement.setString(6, customer.getSsn());

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

    public Customer getCustomer(String username) throws SQLException
    {
        String query_1 =
                "select  customers.first_name,customers.last_name,customers.ssn,customers.email,customers.phone," +
                        "addresses.unit,addresses.street,addresses.city,addresses.state,addresses.zip," +
                        "credentials.password " +
                        "from project0.credentials " +
                        "join project0.addresses on project0.credentials.customer_ssn = project0.addresses.customer_ssn " +
                        "join project0.customers on project0.credentials.customer_ssn = project0.customers.ssn;";

        String query_2 =
                "select * " +
                "from   project0.accounts " +
                "where  project0.accounts.user_name = ?";

        PreparedStatement preparedStatement_1 = connection.prepareStatement(query_1);
        PreparedStatement preparedStatement_2 = connection.prepareStatement(query_2);
        preparedStatement_2.setString(1, username);

        ResultSet resultSet_1 = preparedStatement_1.executeQuery();
        ResultSet resultSet_2 = preparedStatement_2.executeQuery();

        MyList<String> accounts = new MyList<>();
        Customer customer = null;
        if(resultSet_1.next())
        {
            customer = new Customer(resultSet_1.getString("first_name"), resultSet_1.getString("last_name"), resultSet_1.getString("ssn"),
                                    resultSet_1.getString("email"), resultSet_1.getString("phone"), username, resultSet_1.getString("password"),
                                    resultSet_1.getString("unit"), resultSet_1.getString("street"), resultSet_1.getString("city"),
                                    resultSet_1.getString("state"), resultSet_1.getString("zip"));
        }
        while (resultSet_2.next() && customer != null)
        {
            String accountType = resultSet_2.getString("account");
            String number = resultSet_2.getString("number");
            String accountName = "project0.";
            Account account = null;
            switch (accountType)
            {
                case "checking":
                    accountName += "c" + number;
                    account = new CheckingAccount(number);
                    break;
                case "savings":
                    accountName += "s" + number;
                    account = new SavingsAccount(number);
                    break;
                case "trust":
                    accountName += "t" + number;
                    account = new TrustAccount(number);
                    break;
                default:
                    throw new RuntimeException("Illegal account type");
            }
            String query = "select * from " + accountName;
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                account.getTransactions().add(new Transaction(resultSet.getString("transaction"), resultSet.getDouble("amount"),
                                                              resultSet.getDouble("balance")));
            }
            customer.getAccounts().add(account);
        }
        return customer;
    }

    public String addAccount(char identifier) throws SQLException
    {
        int lastTableNumber = 0;
        String query = "select max(number) from project0.accounts;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
        {
            lastTableNumber = resultSet.getInt(1);
        }
        if (lastTableNumber != 0)
        {
            String newTableName = "project0." + identifier + ++lastTableNumber;
            query = "create table " + newTableName +" (" +
                    "id serial not null ," +
                    "transaction varchar(10) not null ," +
                    "amount decimal not null," +
                    "balance decimal not null)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
//            preparedStatement1.setString(1, newTableName);
            preparedStatement1.executeUpdate();
            query = "insert into " + newTableName + " values(1,'deposit',0,0);";
            preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.executeUpdate();
            String accountLabel = null;
            switch(identifier)
            {
                case 'c':
                    accountLabel = "checking";
                    break;
                case 's':
                    accountLabel = "savings";
                    break;
                case 't':
                    accountLabel = "trust";
            }
            preparedStatement1 = null;
            query = "insert into project0.accounts (user_name,account,customer_ssn,number) values(?,?,?,?);";
            preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.setString(1, CurrentCustomer.getInstance().getCustomer().getUsername());
            preparedStatement1.setString(2, accountLabel);
            preparedStatement1.setString(3, CurrentCustomer.getInstance().getCustomer().getSsn());
            preparedStatement1.setInt(4,lastTableNumber);

            preparedStatement1.executeUpdate();
            CurrentCustomer.getInstance().setCustomer(getCustomer(CurrentCustomer.getInstance().getCustomer().getUsername()));
            return newTableName.replace("project0.", "");
        }
        return null;
    }

    public void updateAccount(Account account, String identifier) throws SQLException
    {
        String query = "insert into project0." + identifier + account.getNumber() + " (transaction,amount,balance) values (?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, account.getTransactions().at(account.getTransactions().size() - 1).getType());
        preparedStatement.setDouble(2, account.getTransactions().at(account.getTransactions().size() - 1).getAmount());
        preparedStatement.setDouble(3, account.getTransactions().at(account.getTransactions().size() - 1).getBalance());
        preparedStatement.executeUpdate();

    }
}
