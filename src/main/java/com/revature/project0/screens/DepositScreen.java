package com.revature.project0.screens;

import com.revature.project0.persistance.DAO;
import com.revature.project0.utilities.Controller;
import com.revature.project0.utilities.CurrentAccount;
import com.revature.project0.utilities.InputValidator;

import java.sql.SQLException;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class DepositScreen extends Screen
{
    private Scanner scanner;
    private static DepositScreen instance;

    private DepositScreen(String identifier)
    {
        super(identifier);
        scanner = Controller.getInstance().getScanner();
    }

    public static DepositScreen getInstance()
    {
        if (instance == null)
        {
            instance = new DepositScreen("/deposit");
        }
        return instance;
    }

    @Override
    public void render()
    {
        System.out.print("Enter amount to deposit: ");
        String input = scanner.nextLine();
        try
        {
            if (InputValidator.validate(input, "/deposit") == null)
                return;
            CurrentAccount.getInstance().getAccount().deposit(Double.parseDouble(input));
            DAO.getInstance().updateAccount(CurrentAccount.getInstance().getAccount());

        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
