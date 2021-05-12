package com.revature.project0.utilities;

import com.revature.project0.models.Customer;

public class CurrentCustomer
{
    private Customer customer;
    private static CurrentCustomer instance;

    private CurrentCustomer()
    {
    }

    public static CurrentCustomer getInstance()
    {
        if (instance == null)
        {
            instance = new CurrentCustomer();
        }
        return instance;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }

    public Customer getCustomer()
    {
        return customer;
    }
}
