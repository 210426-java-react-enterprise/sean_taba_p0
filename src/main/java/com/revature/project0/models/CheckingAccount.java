package com.revature.project0.models;

import com.revature.project0.utilities.MyList;

public class CheckingAccount extends Account{

    public CheckingAccount(String number)
    {
        super(number);
    }

    public CheckingAccount(String number, double balance)
    {
        super(number, balance);
    }

    public CheckingAccount(String number, double balance, MyList<Transaction> transactions)
    {
        super(number, balance, transactions);
    }

    @Override
    public String toString()
    {
        return "Checking Account - account number: " + this.getNumber() + " - balance: $" + this.getBalance();
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
        if (amount > balance) return balance;
        this.balance -= amount;
        transactions.add(new Transaction("withdraw", amount, balance));
        return balance;
    }
}
