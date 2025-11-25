package org.lab5;
/**
 * Base exception type for all railway-related errors.
 */
public class RailwayException extends Exception {

    /**
     * Creates a new RailwayException with the given message.
     *
     * @param message description of the problem
     */
    public RailwayException(String message) {
        super(message);
    }

    /**
     * Creates a new RailwayException with message and cause.
     *
     * @param message description of the problem
     * @param cause   original cause of the problem
     */
    public RailwayException(String message, Throwable cause) {
        super(message, cause);
    }
}
