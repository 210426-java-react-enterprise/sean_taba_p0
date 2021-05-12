package com.revature.project0;

import com.revature.project0.utilities.Controller;
import exceptions.IllegalInputException;

public class Driver {

    public static void main(String[] args) throws IllegalInputException
    {
        Controller controller = new Controller();
        controller.run();
    }
}
