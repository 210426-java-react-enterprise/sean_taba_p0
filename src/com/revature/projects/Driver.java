package com.revature.projects;

import com.revature.projects.screens.CreateUserAccountScreen;
import com.revature.projects.screens.StartScreen;
import com.revature.projects.utilities.DAO;
import com.revature.projects.utilities.MyList;

import java.sql.*;
import java.util.Scanner;

public class Driver {

    private static DAO dao;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            dao = new DAO();
        } catch (SQLException e)
        {
            System.err.println("Error connecting to the SQL server");
            return;
        }

        mainLoop:
        while(true) {
            StartScreen startScreen = new StartScreen(scanner);
            int startScreenInput = startScreen.run();
            switch(startScreenInput)
            {
                case 1:
                    while (true) {
                        CreateUserAccountScreen createUserAccountScreen = new CreateUserAccountScreen(scanner);
                        MyList<String> newUserCredentials = createUserAccountScreen.recordNewCredentials();

                        try {
                            if (dao.tryNewCredentials(newUserCredentials)) {
                                System.out.println("\nusername already exists. Choose a different username\n");
                            } else
                            {
                                createUserAccountScreen.run();
                                break;
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    break;
                case 2:


                    break;

                case 3:
                    break mainLoop;

                default:
                    System.err.println("Incorrect return from StartScreen");
                    break mainLoop;
            }


        }

    }
}
