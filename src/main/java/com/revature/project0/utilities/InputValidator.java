package com.revature.project0.utilities;

public class InputValidator {

    public static int validate(String input, int min, int max)
    {
        try
        {
            int number = Integer.parseInt(input);
            if(number >= min && number <= max)
            {
                return number;
            }
            System.out.println("Your entry was out of range. Must be 1,2 or 3.");
        } catch (NumberFormatException e)
        {
            System.out.println("Your entry is not a number");
            return -1;
        }
        return -1;
    }

    public static int validate(String input, String identifier)
    {
        if (input == null) return -1;
        switch (identifier)
        {
            case "/username":
                input = input.stripLeading().stripTrailing();
                if (input.length() < 5 || input.length() > 15)
                {
                    System.out.println("username length must be 5-15 characters.");
                    return -1;
                }
                if (input.chars().allMatch(Character::isLetterOrDigit)) return 0;
                System.out.println("Only letters and numbers are allowed.");
                break;
            case "/password":

                break;

        }
        return -1;
    }
}
