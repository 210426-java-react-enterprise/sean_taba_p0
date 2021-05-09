package com.revature.project0.screens;

import com.revature.project0.models.Customer;
import com.revature.project0.utilities.Controller;
import com.revature.project0.persistance.DAO;
import com.revature.project0.utilities.InputValidator;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateUserAccountScreen extends Screen
{

    private Scanner scanner;
    private static CreateUserAccountScreen instance;

    private CreateUserAccountScreen(String identifier)
    {
        super(identifier);
        scanner = Controller.getInstance().getScanner();
    }

    @Override
    public void render()
    {

        System.out.println("\n\n\n*** Create a new user account ***\n\nEnter your desired username (5 - 15 alphanumeric): "); // edit to accept just
        // alphabetic usernames
        String readLine = scanner.nextLine();
        try {
            String username = InputValidator.validate(readLine, "/username");
            if (username == null) return;

            System.out.println("\nEnter your desired password (8 - 50): ");
            readLine = scanner.nextLine();
            String password = InputValidator.validate(readLine, "/password");
            if (password == null) return;

            System.out.println("Enter your first name (2 - 20 alphabetic):");
            readLine = scanner.nextLine();
            String firstName = InputValidator.validate(readLine, "/name");
            if (firstName == null) return;

            System.out.println("Enter your last name (2 - 20 alphabetic):");
            readLine = scanner.nextLine();
            String lastName = InputValidator.validate(readLine, "/name");
            if (lastName == null) return;

            System.out.println("Enter your social security  (9 numeric):");
            readLine = scanner.nextLine();
            String ssn = InputValidator.validate(readLine, "/ssn");
            if (ssn == null) return;

            System.out.println("Enter your email  (valid email address):");
            readLine = scanner.nextLine();
            String email = InputValidator.validate(readLine, "/email");
            if (email == null) return;

            System.out.println("Enter your phone  (9-13 digits):");
            readLine = scanner.nextLine();
            String phone = InputValidator.validate(readLine, "/phone");
            if (phone == null) return;

            System.out.println("*** Enter your home address ***\n\nunit number (if applies 1-3 digits):");
            readLine = scanner.nextLine();
            String unit = InputValidator.validate(readLine, "/unit");


            System.out.println("Street (10 - 30 alphanumeric): ");
            readLine = scanner.nextLine();
            String street = InputValidator.validate(readLine, "/street");
            if (street == null) return;

            System.out.println("City (2 - 15 alphabetic):");
            readLine = scanner.nextLine();
            String city = InputValidator.validate(readLine, "/city");
            if (city == null) return;

            System.out.println("State (2 - 15 alphabetic):");
            readLine = scanner.nextLine();
            String state = InputValidator.validate(readLine, "/state");
            if (state == null) return;

            System.out.println("Enter your zip code  (5 digits):");
            readLine = scanner.nextLine();
            String zip = InputValidator.validate(readLine, "/zip");
            if (zip == null) return;

            Customer newCustomer = new Customer(firstName, lastName, ssn, email, phone, username, password, unit, street, city, state, zip);
//        Customer newCustomer = new Customer("sean", "taba", "547858965", "sean@gmail.com", "4587458758", "seantaba", "seantabapassword", "", "213313 skjdf  " +
//                "sdfh", "albany", "new york", "47854");
//        try
//        {
            DAO.getInstance().addCustomer(newCustomer);
//        } catch (SQLException throwables)
//        {
//            throwables.printStackTrace();
//        } catch (ClassNotFoundException classNotFoundException)
//        {
//            classNotFoundException.printStackTrace();
//        }

        } catch (SQLException |
                ClassNotFoundException e)

        {
            e.printStackTrace();
        }

}

    public static CreateUserAccountScreen getInstance()
    {
        if (instance == null)
        {
            instance = new CreateUserAccountScreen("/create user");
        }
        return instance;
    }


}
