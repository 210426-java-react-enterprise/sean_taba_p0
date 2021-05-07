package com.revature.project0.screens;

import com.revature.project0.utilities.Controller;
import com.revature.project0.utilities.InputValidator;
import com.revature.project0.utilities.MyList;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateUserAccountScreen extends Screen {

    private Scanner scanner;
    private static CreateUserAccountScreen instance;

    private CreateUserAccountScreen(String identifier) {
        super(identifier);
        scanner = Controller.getInstance().getScanner();
    }

    @Override
    public void render() {
        clearScreen();
        System.out.println("\n\n\n*** Create a new user account ***\n\nEnter your desired username: ");
        String readLine = scanner.nextLine();
        try {
            if (InputValidator.validate(readLine, "/username") == null) return;
            String username = readLine;
            System.out.println("\nEnter your desired username: ");
            readLine = scanner.nextLine();
            if (InputValidator.validate(readLine, "/password") == null) return;
            String password = readLine;

        } catch (SQLException e)
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
