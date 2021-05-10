package com.revature.project0;

import com.revature.project0.utilities.Controller;
import com.revature.project0.utilities.ScreenManager;
import org.codehaus.plexus.util.cli.CommandLineUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {


    public static void main(String[] args) {

        while (Controller.getInstance().isAppRunning())
        {
            ScreenManager.getInstance().navigate("/start");
        }

//        Pattern pattern = Pattern.compile("^[cst]\\d\\d\\d$");
//        Matcher matcher = pattern.matcher("t102");
//        System.out.println(matcher.matches());

//        String str = "1012";
//        System.out.println(str.chars().allMatch(Character::isDigit));
    }

}
