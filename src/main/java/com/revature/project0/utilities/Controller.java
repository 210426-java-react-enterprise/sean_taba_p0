package com.revature.project0.utilities;

import java.util.Scanner;

public class Controller {

    boolean appRunning;
    private final Scanner scanner;
    private static Controller instance;

    private Controller()
    {
        appRunning = true;
        scanner = new Scanner(System.in);
    }

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new Controller();
        }
        return instance;
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
