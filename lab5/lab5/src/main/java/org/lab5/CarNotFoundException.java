package org.lab5;

/**
 * Exception thrown when no suitable car is found in the train.
 */
public class CarNotFoundException extends RailwayException {

    public CarNotFoundException(String message) {
        super(message);
    }
}

