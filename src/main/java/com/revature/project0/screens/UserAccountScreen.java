package com.revature.project0.screens;

import com.revature.project0.utilities.Controller;
import com.revature.project0.persistance.DAO;
import com.revature.project0.utilities.MyList;

import java.sql.SQLException;
import java.util.Scanner;

public class UserAccountScreen extends Screen {
    private String username;
    private static UserAccountScreen instance;
    private Scanner scanner;
    private MyList<String> accounts;

    private UserAccountScreen(String identifier)
    {
        super(identifier);
        scanner = Controller.getInstance().getScanner();
    }

    public static UserAccountScreen getInstance()
    {
        if (instance == null)
        {
            instance = new UserAccountScreen("/accounts");
        }
        return instance;
    }

    @Override
    public void render()
    {
        System.out.println(String.format("\n*** %s's accounts ***\n", username));

        try
        {
            accounts = DAO.getInstance().getAccounts(username);
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        if (accounts.size() != 0)
        {
            for (int i = 0; i < accounts.size(); i++)
            {
                System.out.println(String.format("%d - %s", i + 1, accounts.get(i)));
            }
        }


    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
