package com.sales.messaging.exception;

/**
 * Created by riham.y.abdelmaksoud on 11/13/2018.
 */
public class InvalidInputException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidInputException(String string) {
        super(string);
    }
}
