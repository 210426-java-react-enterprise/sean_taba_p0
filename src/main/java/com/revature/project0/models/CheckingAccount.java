package com.revature.project0.models;

public class CheckingAccount extends Account{


    public CheckingAccount(String name)
    {
        super(name);
    }

    public CheckingAccount(String name, double balance)
    {
        super(name, balance);
    }

    @Override
    public double deposit(double amount)
    {
        return 0;
    }

    @Override
    public double withdraw(double amount)
    {
        return 0;
    }
}
