package com.airtribe.learntrack.util;

import com.airtribe.learntrack.exception.InvalidInputException;

public class InputValidator {
    public static int validatePositiveInt(String value) {

        try{
            int number = Integer.parseInt(value);
            if (number <= 0) {
                throw new InvalidInputException("Input must be a positive integer.");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Input must be a positive integer.");
        }
        
    }

    public static boolean validateBoolean(String value) {
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        } else {
            throw new InvalidInputException("Input must be a boolean value (true/false).");
        }
    }

    public static String validateNonEmptyString(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException("Input string cannot be empty.");
        }
        return value.trim();
    }

    public static String validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new InvalidInputException("Invalid email format.");
        }
        return email;
    }

    public static int validateChoice(String value, int min, int max) {
        try {
            int choice = validatePositiveInt(value);
            if (choice < min || choice > max) {
                throw new InvalidInputException("Choice must be between " + min + " and " + max + ".");
            }
            return choice;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Choice must be a valid integer.");
        }
    }
}
