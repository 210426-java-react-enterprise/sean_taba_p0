package com.revature.project0.models;

import com.revature.project0.utilities.MyList;
import exceptions.IllegalInputException;

public abstract class Account {

    protected double balance;
    protected String number;
    protected MyList<Transaction> transactions;

    protected Account(String number)
    {
        this.number = number;
        balance = 0;
        transactions = new MyList<>();
    }
    protected Account(String number, double balance)
    {
        this.number = number;
        this.balance = balance;
        transactions = new MyList<>();
    }

    public Account(String number, double balance, MyList<Transaction> transactions)
    {
        this.balance = balance;
        this.number = number;
        this.transactions = transactions;
    }

    public double getBalance()
    {
        return balance;
    }

    public String getNumber()
    {
        return number;
    }

    public MyList<Transaction> getTransactions()
    {
        return transactions;
    }

    public void updateBalance()
    {
        balance = transactions.getLast().getBalance();
    }
    public abstract double deposit(double amount) throws IllegalInputException;
    public abstract double withdraw(double amount) throws IllegalInputException;
}
