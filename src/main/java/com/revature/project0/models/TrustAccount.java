package com.revature.project0.models;

import com.revature.project0.utilities.MyList;
import exceptions.IllegalInputException;

public class TrustAccount extends Account
{
    public TrustAccount(String number)
    {
        super(number);
    }

    public TrustAccount(String number, double balance)
    {
        super(number, balance);
    }

    public TrustAccount(String number, double balance, MyList<Transaction> transactions)
    {
        super(number, balance, transactions);
    }

    @Override
    public String toString()
    {
        return String.format("%s %s %s%.2f", "Trust Account - account number: ",number," - balance: $",this.getBalance());
    }

    @Override
    public double deposit(double amount) throws IllegalInputException
    {
        this.balance += amount;
        transactions.add(new Transaction("deposit", amount, balance));
        return balance;
    }

    @Override
    public double withdraw(double amount) throws IllegalInputException
    {
        if (amount > balance) return balance;
        this.balance -= amount;
        transactions.add(new Transaction("withdraw", amount, balance));
        return balance;
    }
}
