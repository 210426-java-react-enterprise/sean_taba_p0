package com.revature.project0.screens;

import com.revature.project0.utilities.Controller;
import com.revature.project0.persistance.DAO;
import com.revature.project0.utilities.CurrentCustomer;
import com.revature.project0.utilities.InputValidator;
import com.revature.project0.utilities.ScreenManager;

import java.sql.SQLException;
import java.util.Scanner;

public class UserAccountLoginScreen extends Screen {

    private Scanner scanner;
    private static UserAccountLoginScreen instance;

    private UserAccountLoginScreen(String identifier)
    {
        super(identifier);
        scanner = Controller.getInstance().getScanner();
    }

    public static UserAccountLoginScreen getInstance()
    {
        if (instance == null)
        {
            instance = new UserAccountLoginScreen("/login");
        }
        return instance;
    }

    @Override
    public void render()
    {
        try
        {
            System.out.println("\n\n*** Login to your account ***\n");
            System.out.println("Enter your username: ");
            String input = scanner.nextLine();
            String username = InputValidator.validate(input, "/isUsername");
            if (username == null)
                return;

            System.out.println("Enter your password: ");
            input = scanner.nextLine();
            String password = InputValidator.validate(input, "/password");
            if (password == null)
                return;
            if(password.equals(DAO.getInstance().isCorrectPassword(username)))
            {
                CurrentCustomer.getInstance().setCustomer(DAO.getInstance().getCustomer(username));
                ScreenManager.getInstance().navigate("/customer account");
            } else
            {
                System.out.println("Password was not correct. Please try again.");
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
