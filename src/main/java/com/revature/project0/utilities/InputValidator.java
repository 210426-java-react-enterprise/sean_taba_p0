package com.revature.project0.utilities;

import com.revature.project0.persistance.DAO;

import java.sql.SQLException;

public class InputValidator {

    private static DAO dao;

    static {
        try {
            dao = DAO.getInstance();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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

    public static String validate(String input, String identifier) throws SQLException
    {
        if (input == null) return null;
        input = input.trim();
        switch (identifier)
        {
            case "/username":
            case "/isUsername":
                if (!isCorrectLength(input, 5, 15))
                {
                    System.out.println("username length must be 5-15 characters.");
                    return null;
                }
                if (!input.chars().allMatch(Character::isLetterOrDigit))
                {
                    System.out.println("Only letters and numbers are allowed.");
                    return null;
                }
                if(identifier.equals("/username") && dao.tryNewUsername(input))
                {
                    System.out.println("username entered has already been taken. Use a different username.");
                    return null;
                }
                if (identifier.equals("/isUsername") && !dao.tryNewUsername(input))
                {
                    System.out.println("username does not exist. Please try again.");
                    return null;
                }
                return input;

            case "/password":
                if (!isCorrectLength(input, 8, 50))
                {
                    System.out.println("password length must be 8-50 character.");
                    return null;
                }
                return input;

            case "/name":
                if (!isCorrectLength(input, 2, 20))
                {
                    System.out.println("First name length must be 2-20 character.");
                    return null;
                }
                if (!input.chars().allMatch(Character::isLetter))
                {
                    System.out.println("User name can only contain letters.");
                    return null;
                }
                return input;

            case "/ssn":
                if (!isCorrectLength(input, 9, 9))
                {
                    System.out.println("SSN length must be 9 digits.");
                    return null;
                }
                if (!input.chars().allMatch(Character::isDigit))
                {
                    System.out.println("SSN can only contain digits.");
                    return null;
                }
                if (dao.tryNewSSN(input))
                {
                    System.out.println("SSN has already been used. Please login to your account instead.");
                    return null;
                }
                return input;

            case "/email":
                if (!input.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
                {
                    System.out.println("Email address is not valid. Please try again.");
                    return null;
                }
                return input;

            case "/phone":
                if (!isCorrectLength(input, 9, 13))
                {
                    System.out.println("Phone number length must be 9-13 digits.");
                    return null;
                }
                if (!input.chars().allMatch(Character::isDigit))
                {
                    System.out.println("phone number can only contain digits.");
                    return null;
                }
                return input;

            case "/unit":
                if (!isCorrectLength(input, 1, 3))
                {
                    System.out.println("Unit number length must be 1-3 digits.");
                    return null;
                }
                if (!input.equals("") && !input.chars().allMatch(Character::isDigit))
                {
                    System.out.println("Unit number can only contain digits.");
                    return null;
                }
                return input;

            case "/street":
                if (!isCorrectLength(input, 10, 30))
                {
                    System.out.println("Street length must be 10-30 characters.");
                    return null;
                }
                if (!input.replace(' ', 'w').chars().allMatch(Character::isLetterOrDigit))
                {
                    System.out.println("Only letters and numbers are allowed.");
                    return null;
                }
                return input;

            case "/city":
            case "/state":
                if (!isCorrectLength(input, 2, 15))
                {
                    if (identifier.equals("/city"))
                    {
                        System.out.println("City name length must be 2-15 character.");
                    } else
                    {
                        System.out.println("State name length must be 2-15 character.");
                    }
                    return null;
                }
                if (!input.replace(' ', 'w').chars().allMatch(Character::isLetter))
                {
                    System.out.println("City name can only contain letters.");
                    return null;
                }
                return input;

            case "/zip":
                if (!isCorrectLength(input, 5, 5))
                {
                    System.out.println("Zip code length must be 5 digits.");
                    return null;
                }
                if (!input.chars().allMatch(Character::isDigit))
                {
                    System.out.println("Zip code can only contain digits.");
                    return null;
                }
                return input;


        }
        return null;
    }

    private static boolean isCorrectLength(String input, int lowerBound, int higherBound)
    {
        if (input.length() < lowerBound || input.length() > higherBound) return false;
        return true;
    }
}
