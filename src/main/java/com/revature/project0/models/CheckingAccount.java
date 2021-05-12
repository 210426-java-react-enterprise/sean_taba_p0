package com.revature.project0.models;

import com.revature.project0.utilities.MyList;
import exceptions.IllegalInputException;

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
        return String.format("%s %s %s%.2f", "Checking Account - account number: ",number," - balance: $",this.getBalance());
    }

    @Override
    public double deposit(double amount) throws IllegalInputException
    {
        this.balance += amount;
        transactions.add(new Transaction("deposit", amount, balance));
        System.out.println("Deposit successful.");
        return balance;
    }

    @Override
    public double withdraw(double amount) throws IllegalInputException
    {
        if (amount > balance)
        {
            System.out.println("Amount specified is greater than the balance. Please try again.");
            return -1;
        }
        this.balance -= amount;
        transactions.add(new Transaction("withdraw", amount, balance));
        System.out.println("Withdrawal successful.");
        return balance;
    }
}
