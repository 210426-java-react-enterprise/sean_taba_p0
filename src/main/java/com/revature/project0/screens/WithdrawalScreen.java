package com.revature.project0.screens;

import com.revature.project0.models.CheckingAccount;
import com.revature.project0.models.SavingsAccount;
import com.revature.project0.models.TrustAccount;
import com.revature.project0.persistance.DAO;
import com.revature.project0.utilities.Controller;
import com.revature.project0.utilities.CurrentAccount;
import com.revature.project0.utilities.InputValidator;
import exceptions.IllegalInputException;

import java.sql.SQLException;
import java.util.Scanner;

public class WithdrawalScreen extends Screen
{
    private Scanner scanner;
    private static WithdrawalScreen instance;

    private WithdrawalScreen(String identifier)
    {
        super(identifier);
        scanner = Controller.getInstance().getScanner();
    }

    public static WithdrawalScreen getInstance()
    {
        if (instance == null)
        {
            instance = new WithdrawalScreen("/withdraw");
        }
        return instance;
    }

    @Override
    public void render()
    {
        System.out.print("Enter amount to withdraw: ");
        String input = scanner.nextLine();
        try
        {
            if (InputValidator.validate(input, "/withdraw") == null)
                return;

            if(CurrentAccount.getInstance().getAccount().withdraw(Double.parseDouble(input)) != -1)
                DAO.getInstance().updateAccount(CurrentAccount.getInstance().getAccount());

        } catch (SQLException | ClassNotFoundException | IllegalInputException e)
        {
            e.printStackTrace();
        }

    }
}
