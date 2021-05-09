package com.revature.project0.screens;

import com.revature.project0.models.Customer;
import com.revature.project0.utilities.Controller;
import com.revature.project0.persistance.DAO;
import com.revature.project0.utilities.CurrentCustomer;
import com.revature.project0.utilities.MyList;

import java.sql.SQLException;
import java.util.Scanner;

public class UserAccountScreen extends Screen {

    private static UserAccountScreen instance;
    private Scanner scanner;


    private UserAccountScreen(String identifier)
    {
        super(identifier);
        scanner = Controller.getInstance().getScanner();
    }

    public static UserAccountScreen getInstance()
    {
        if (instance == null)
        {
            instance = new UserAccountScreen("/customer account");
        }
        return instance;
    }

    @Override
    public void render()
    {
        while(true)
        {
            Customer customer = CurrentCustomer.getInstance().getCustomer();
            System.out.println(String.format("\n*** %s's accounts ***\n", customer.getFirstName()));

            if (customer.getAccounts().size() != 0)
            {
                for (int i = 0; i < customer.getAccounts().size(); i++)
                {
                    System.out.println(String.format("%d - %s", i + 1, customer.getAccounts().get(i)));
                }
            }
        }
    }


}
