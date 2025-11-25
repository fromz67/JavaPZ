package org.lab5;

/**
 * Exception thrown when the passenger or baggage capacity is exceeded.
 */
public class CapacityExceededException extends RailwayException {

    public CapacityExceededException(String message) {
        super(message);
    }
}
