package com.revature.project0.models;

public abstract class Account {
    private String name;
    private double balance;

    protected Account(String name)
    {
        this.name = name;
        balance = 0;
    }
    protected Account(String name, double balance)
    {
        this.name = name;
        this.balance = balance;
    }

    public abstract double deposit(double amount);
    public abstract double withdraw(double amount);
}
