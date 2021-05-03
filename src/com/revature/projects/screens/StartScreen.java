package com.revature.projects.screens;

import exceptions.IllegalInputException;

import java.util.Scanner;

public class StartScreen {
    Scanner scanner;

    public StartScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public int run()
    {
        int input = 0;
        while(true) {
            System.out.println("\n\nWelcome to Sean's banking app.");
            System.out.println("******************************\n");
            System.out.print("What would you like to do,\n\n1.Create an account\n2.Login to your account\n3.Quit\n\nPlease enter your choice: ");
            try {
                String readLine = scanner.nextLine();
                input = Integer.parseInt(readLine);
                if (input > 0 && input < 4)
                    break;
                throw new Exception();
            } catch(Exception e)
            {
                System.err.println("\n*** You have entered an illegal character. Please enter 1,2 or 3 ***\n");
            }


        }
        return input;
    }
}
