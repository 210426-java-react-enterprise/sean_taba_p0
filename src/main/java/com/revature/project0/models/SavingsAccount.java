package com.revature.project0.models;

import com.revature.project0.utilities.MyList;

public class SavingsAccount extends Account{
    public SavingsAccount(String number)
    {
        super(number);
    }

    public SavingsAccount(String number, double balance)
    {
        super(number, balance);
    }

    public SavingsAccount(String number, double balance, MyList<Transaction> transactions)
    {
        super(number, balance, transactions);
    }

    @Override
    public String toString()
    {
        return "Savings Account - account number: " + this.getNumber() + " - balance: " + this.getBalance();
    }
    @Override
    public double deposit(double amount)
    {
        this.balance += amount;
        transactions.add(new Transaction("deposit", amount, balance));
        return balance;
    }

    @Override
    public double withdraw(double amount)
    {
        this.balance -= amount;
        transactions.add(new Transaction("withdraw", amount, balance));
        return balance;
    }
}
