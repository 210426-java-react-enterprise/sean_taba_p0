package com.revature.project0.screens;

import com.revature.project0.utilities.Controller;
import com.revature.project0.utilities.InputValidator;
import com.revature.project0.utilities.ScreenManager;

import java.util.Scanner;

public class StartScreen extends Screen {

    private Scanner scanner;
    private static StartScreen instance;

    private StartScreen(String identifier) {
        super(identifier);
        scanner = Controller.getInstance().getScanner();
    }

    public static StartScreen getInstance()
    {
        if (instance == null)
        {
            instance = new StartScreen("/start");
        }
        return instance;
    }

    @Override
    public void render() {
        int input = 0;

        clearScreen();
        System.out.println("\n\nWelcome to Sean's banking app.");
        System.out.println("******************************\n");
        System.out.print("What would you like to do,\n\n1.Create an account\n2.Login to your account\n3.Quit\n\nPlease enter your choice: ");
        try {
            String readLine = scanner.nextLine();
            input = InputValidator.validate(readLine,1, 3);
            if(input != -1)
            {
                switch (input)
                {
                    case 1:
                        ScreenManager.getInstance().navigate("/create user");
                        break;
                    case 2:

                        break;
                    case 3:
                        clearScreen();
                        System.out.println("Thank you for visiting.\n\nShutting down!\n");
                        Controller.getInstance().setAppRunning(false);
                }
            }

        } catch (Exception e) {
            System.err.println("\n*** You have entered an illegal character. Please enter 1,2 or 3 ***\n");
        }


    }
}
