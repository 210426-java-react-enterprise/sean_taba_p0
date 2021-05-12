package com.revature.project0.screens;

import com.revature.project0.persistance.DAO;
import com.revature.project0.utilities.CurrentAccount;
import com.revature.project0.utilities.InputValidator;
import exceptions.IllegalInputException;

import java.sql.SQLException;
import java.util.Scanner;

public class DepositScreen extends Screen
{
    private Scanner scanner;
    private InputValidator inputValidator;
    private DAO dao;

    public DepositScreen(Scanner scanner, InputValidator inputValidator, DAO dao)
    {
        super("/deposit");
        this.scanner = scanner;

        this.inputValidator = inputValidator;
        this.dao = dao;
    }

    @Override
    public void render()
    {
        System.out.print("Enter amount to deposit: ");
        String input = scanner.nextLine();
        try
        {
            if (inputValidator.validate(input, "/deposit") == null)
                return;
            String identifier = "";
           CurrentAccount.getInstance().getAccount().deposit(Double.parseDouble(input));

            dao.updateAccount(CurrentAccount.getInstance().getAccount());

        } catch (SQLException | IllegalInputException e)
        {
            e.printStackTrace();
        }
    }
}
