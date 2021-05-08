package com.revature.project0;

import com.revature.project0.utilities.Controller;
import com.revature.project0.utilities.ScreenManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {


    public static void main(String[] args) {

        while (Controller.getInstance().isAppRunning())
        {
            ScreenManager.getInstance().navigate("/start");
        }

//        Pattern pattern = Pattern.compile("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
//        Matcher matcher = pattern.matcher("sean@gmail.com");
//        System.out.println(matcher.matches());
    }

}
