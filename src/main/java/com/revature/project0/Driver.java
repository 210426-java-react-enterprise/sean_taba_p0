package com.revature.project0;

import com.revature.project0.utilities.Controller;
import com.revature.project0.utilities.ScreenManager;

public class Driver {


    public static void main(String[] args) {

        while (Controller.getInstance().isAppRunning())
        {
            ScreenManager.getInstance().navigate("/start");
        }
    }

}
