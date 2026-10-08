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
}
