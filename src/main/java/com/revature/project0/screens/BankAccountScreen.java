package com.revature.project0.screens;

import com.revature.project0.models.Transaction;
import com.revature.project0.utilities.CurrentAccount;

public class BankAccountScreen extends Screen{

    public BankAccountScreen()
    {
        super("transactions");
    }

    @Override
    public void render()
    {
        System.out.println("\n" + CurrentAccount.getInstance().getAccount() + "\n");

        for (Transaction transaction : CurrentAccount.getInstance().getAccount().getTransactions())
        {
            System.out.println(transaction);
        }
    }
}
