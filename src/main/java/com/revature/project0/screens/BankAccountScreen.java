package com.revature.project0.screens;

import com.revature.project0.models.Account;
import com.revature.project0.models.Transaction;
import com.revature.project0.utilities.CurrentAccount;

public class BankAccountScreen extends Screen{

    protected BankAccountScreen(String identifier)
    {
        super(identifier);
    }

    @Override
    public void render()
    {
        Account account = CurrentAccount.getInstance().getAccount();
        System.out.println("\n" + account + "\n");
        for (Transaction transaction : account.getTransactions())
        {
            System.out.println(transaction);
        }
    }
}
