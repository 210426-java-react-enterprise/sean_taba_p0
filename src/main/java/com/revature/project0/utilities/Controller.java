package com.revature.project0.utilities;

import com.revature.project0.persistance.ConnectionManager;
import com.revature.project0.persistance.DAO;
import com.revature.project0.screens.*;
import exceptions.IllegalInputException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.Scanner;

public class Controller {

    boolean appRunning;
    private final Scanner scanner;
    private final Connection connection;
    private final Properties properties;
    private final ConnectionManager connectionManager;
    private final DAO dao;
    private final InputValidator inputValidator;
    private final MyList<Screen> screens;
    private final StartScreen startScreen;
    private final CreateUserAccountScreen createUserAccountScreen;
    private final UserAccountLoginScreen userAccountLoginScreen;
    private final UserAccountScreen userAccountScreen;
    private final DepositScreen depositScreen;
    private final WithdrawalScreen withdrawalScreen;
    private final BankAccountScreen bankAccountScreen;
    private final ScreenManager screenManager;


    public Controller()
    {
        properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/application.properties"));

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        this.connectionManager = new ConnectionManager(properties);
        this.connection = this.connectionManager.getConnection();

        this.dao = new DAO(connection);

        this.inputValidator = new InputValidator(dao);

        scanner = new Scanner(System.in);
        this.screens = new MyList<>();
        this.screenManager = new ScreenManager(screens);

        this.startScreen = new StartScreen(scanner,inputValidator, screenManager,this);
        this.createUserAccountScreen = new CreateUserAccountScreen(scanner, inputValidator, dao);
        this.userAccountLoginScreen = new UserAccountLoginScreen(scanner, inputValidator, screenManager, dao);
        this.depositScreen = new DepositScreen(scanner, inputValidator, dao);
        this.withdrawalScreen = new WithdrawalScreen(scanner, inputValidator, dao);
        this.bankAccountScreen = new BankAccountScreen();
        this.userAccountScreen = new UserAccountScreen(scanner, inputValidator, dao, screenManager);

        try
        {
            screens.add(startScreen);
            screens.add(createUserAccountScreen);
            screens.add(userAccountLoginScreen);
            screens.add(depositScreen);
            screens.add(withdrawalScreen);
            screens.add(bankAccountScreen);
            screens.add(userAccountScreen);
        } catch (IllegalInputException e)
        {
            e.printStackTrace();
        }

        appRunning = true;
    }

    public void run()
    {
        while (appRunning)
        {
            try
            {
                screenManager.navigate("/start");
            } catch (IllegalInputException e)
            {
                e.printStackTrace();
            }
        }
    }
    public Scanner getScanner()
    {
        return scanner;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }
}
