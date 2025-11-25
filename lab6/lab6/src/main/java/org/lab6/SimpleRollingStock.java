package org.lab6;

/**
 * Simple implementation of rolling stock for demonstration purposes.
 * Represents, for example, a passenger car with integer capacity.
 */
public class SimpleRollingStock extends RollingStock<Integer> {

    /**
     * Creates a new simple rolling stock instance.
     *
     * @param id       unique identifier
     * @param capacity passenger capacity
     */
    public SimpleRollingStock(String id, int capacity) {
        super(id, capacity);
    }
}
