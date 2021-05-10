package com.revature.project0.screens;

import com.revature.project0.utilities.Controller;

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

    }
}
