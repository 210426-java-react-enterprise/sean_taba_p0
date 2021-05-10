package com.revature.project0.screens;

import com.revature.project0.models.CheckingAccount;
import com.revature.project0.models.SavingsAccount;
import com.revature.project0.models.TrustAccount;
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
            String identifier = "";
            CurrentAccount.getInstance().getAccount().deposit(Double.parseDouble(input));
            if (CurrentAccount.getInstance().getAccount() instanceof CheckingAccount)
            {
                identifier = "c";
            } else if (CurrentAccount.getInstance().getAccount() instanceof SavingsAccount)
            {
                identifier = "s";
            } else if (CurrentAccount.getInstance().getAccount() instanceof TrustAccount)
            {
                identifier = "t";
            }
            DAO.getInstance().updateAccount(CurrentAccount.getInstance().getAccount(), identifier);

        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
