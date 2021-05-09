package com.revature.project0.models;

public class Transaction
{
    private final String type;
    private final double amount;
    private final double balance;

    public Transaction(String type, double amount, double balance)
    {
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public String getType()
    {
        return type;
    }

    public double getAmount()
    {
        return amount;
    }

    public double getBalance()
    {
        return balance;
    }
}
