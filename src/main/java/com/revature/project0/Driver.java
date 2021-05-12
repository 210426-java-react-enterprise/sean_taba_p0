package com.revature.project0;

import com.revature.project0.utilities.Controller;
import com.revature.project0.utilities.ScreenManager;
import exceptions.IllegalInputException;

public class Driver {


    public static void main(String[] args) throws IllegalInputException
    {
        while (Controller.getInstance().isAppRunning())
        {
            ScreenManager.getInstance().navigate("/start");
        }
    }

}
