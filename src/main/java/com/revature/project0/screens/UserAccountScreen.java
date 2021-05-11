package com.revature.project0.screens;

import com.revature.project0.models.Account;
import com.revature.project0.models.Customer;
import com.revature.project0.models.Transaction;
import com.revature.project0.utilities.*;
import com.revature.project0.persistance.DAO;
import exceptions.IllegalInputException;

import java.sql.SQLException;
import java.util.Scanner;

public class UserAccountScreen extends Screen
{

    private static UserAccountScreen instance;
    private Scanner scanner;


    private UserAccountScreen(String identifier)
    {
        super(identifier);
        scanner = Controller.getInstance().getScanner();
    }

    public static UserAccountScreen getInstance()
    {
        if (instance == null)
        {
            instance = new UserAccountScreen("/customer account");
        }
        return instance;
    }

    private Account getCustomerAccount()
    {
        System.out.print("Enter the account number to use: ");
        String input = scanner.nextLine();
        try
        {
            if (InputValidator.validate(input, "/account number") == null)
                return null;

            Account account = null;
            for (Account acc : CurrentCustomer.getInstance().getCustomer().getAccounts())
            {
                if (acc.getNumber().equals(input))
                {
                    account = acc;
                    return account;
                }
            }
            return null;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void render() throws IllegalInputException
    {
        main:
        while (true)
        {
            Customer customer = CurrentCustomer.getInstance().getCustomer();
            System.out.println(String.format("\n*** %s's accounts ***\n", customer.getFirstName()));

            if (customer.getAccounts().size() != 0)
            {
                for (int i = 0; i < customer.getAccounts().size(); i++)
                {
                    System.out.println(String.format("%d - %s", i + 1, customer.getAccounts().get(i)));
                }
            }

            System.out.println("\nPlease choose an option from the menu:\n");
            System.out.println("1 - Add a new account");
            System.out.println("2 - Add a new transaction");
            System.out.println("3 - View account");
            System.out.println("4 - Logout");
            System.out.print("\nchoice: ");
            String input = scanner.nextLine();
            int choice = InputValidator.validate(input, 1, 4);
            if (choice != -1)
            {
                switch (choice)
                {
                    case 1:
                        System.out.println("\nWhat type of account would you like to open?");
                        System.out.println("1 - Checking");
                        System.out.println("2 - Savings");
                        System.out.println("3 - Trust");
                        System.out.print("\nchoice: ");
                        input = scanner.nextLine();
                        choice = InputValidator.validate(input, 1, 3);
                        try
                        {
                            String newAccountNumber = "";
                            switch (choice)
                            {
                                case 1:
                                    newAccountNumber = DAO.getInstance().addAccount('c');
                                    break;
                                case 2:
                                    newAccountNumber = DAO.getInstance().addAccount('s');
                                    break;
                                case 3:
                                    newAccountNumber = DAO.getInstance().addAccount('t');
                            }
                            if (newAccountNumber != null)
                                System.out.println("\nAccount was created successfully. Your new account number is " + newAccountNumber);
                        } catch (SQLException | ClassNotFoundException | IllegalInputException e)
                        {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        if (CurrentCustomer.getInstance().getCustomer().getAccounts().size() == 0)
                        {
                            System.out.println("You have no accounts to make transactions. Please create an account first.");
                            break;
                        }
                        Account account = getCustomerAccount();
                        if (account == null) break;

                        System.out.println("\nWhat would you like to do?\n");
                        System.out.println("1 - Make a deposit");
                        System.out.println("2 - Make a withdrawal");
                        System.out.println("3 - Go back");
                        System.out.print("\nChoice: ");
                        input = scanner.nextLine();

                        choice = InputValidator.validate(input, 1, 4);
                        if (choice == -1)
                            break;

                        switch (choice)
                        {
                            case 1:
                                CurrentAccount.getInstance().setAccount(account);
                                ScreenManager.getInstance().navigate("/deposit");
                                break;
                            case 2:
                                CurrentAccount.getInstance().setAccount(account);
                                ScreenManager.getInstance().navigate("/withdraw");
                            case 3:
                                break;
                        }

                        break;
                    case 3:
                        Account account1 = getCustomerAccount();
                        if (account1 == null) break;

                        System.out.println("\n" + account1 + "\n");
                        for (Transaction transaction : account1.getTransactions())
                        {
                            System.out.println(transaction);
                        }

                        break;

                    case 4:
                        CurrentCustomer.getInstance().setCustomer(null);
                        CurrentAccount.getInstance().setAccount(null);
                        break main;

                }
            }
        }
    }


}
