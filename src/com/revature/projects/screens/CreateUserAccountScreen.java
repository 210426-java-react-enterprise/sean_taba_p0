package com.revature.projects.screens;

import com.revature.projects.utilities.MyList;

import java.util.Scanner;

public class CreateUserAccountScreen {

    private Scanner scanner;

    public CreateUserAccountScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public MyList<String> recordNewCredentials()
    {
        System.out.println("\n\n\n*** Create a new user account ***\n\nEnter your desired username: ");
        String username = scanner.nextLine();

        MyList<String> list = new MyList<>();

        while(true) {
            System.out.println("Enter your new password (Must be at least 8 characters): ");
            String password = scanner.nextLine();
            if (password.length() >= 8)
            {
                list.add(username);
                list.add(password);
                break;
            }
            System.out.println("\nPassword must be 8 characters long.\n");
        }
        return list;
    }


    public MyList<String> run()
    {

        System.out.println("Please enter your personal information. All fields are required.\n\n");

        return new MyList<>();
    }
}
