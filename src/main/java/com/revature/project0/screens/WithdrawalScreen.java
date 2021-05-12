package com.revature.project0.screens;

import com.revature.project0.persistance.DAO;
import com.revature.project0.utilities.CurrentAccount;
import com.revature.project0.utilities.InputValidator;
import exceptions.IllegalInputException;

import java.sql.SQLException;
import java.util.Scanner;

public class WithdrawalScreen extends Screen
{
    private Scanner scanner;
    private InputValidator inputValidator;
    private DAO dao;

    public WithdrawalScreen(Scanner scanner, InputValidator inputValidator, DAO dao)
    {
        super("/withdraw");

        this.scanner = scanner;
        this.inputValidator = inputValidator;
        this.dao = dao;
    }

    @Override
    public void render()
    {
        System.out.print("Enter amount to withdraw: ");
        String input = scanner.nextLine();
        try
        {
            if (inputValidator.validate(input, "/withdraw") == null)
                return;

            if(CurrentAccount.getInstance().getAccount().withdraw(Double.parseDouble(input)) != -1)
                dao.updateAccount(CurrentAccount.getInstance().getAccount());

        } catch (SQLException | IllegalInputException e)
        {
            e.printStackTrace();
        }

    }
}
