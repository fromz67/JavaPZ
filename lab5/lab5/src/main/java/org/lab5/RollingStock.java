package org.lab5;

/**
 * Generic base class for any rolling stock on a railway.
 *
 * @param <T> type of the main capacity parameter
 */
public abstract class RollingStock<T extends Number> {

    private final String id;
    private final double tareWeight; // empty weight in tons
    private final T capacity;

    /**
     * Creates a new rolling stock instance.
     *
     * @param id         unique identifier of the unit
     * @param tareWeight empty weight (in tons)
     * @param capacity   main capacity parameter (e.g., passenger count)
     * @throws IllegalArgumentException if any numeric value is negative
     */
    protected RollingStock(String id, double tareWeight, T capacity) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID must not be null or blank");
        }
        if (tareWeight < 0) {
            throw new IllegalArgumentException("Tare weight must be non-negative");
        }
        if (capacity == null || capacity.doubleValue() < 0) {
            throw new IllegalArgumentException("Capacity must be non-negative");
        }
        this.id = id;
        this.tareWeight = tareWeight;
        this.capacity = capacity;
    }

    /**
     * Returns unique identifier of this rolling stock unit.
     *
     * @return identifier string
     */
    public String getId() {
        return id;
    }

    /**
     * Returns empty weight in tons.
     *
     * @return tare weight
     */
    public double getTareWeight() {
        return tareWeight;
    }

    /**
     * Returns main capacity parameter.
     *
     * @return capacity value
     */
    public T getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "RollingStock{" +
                "id='" + id + '\'' +
                ", tareWeight=" + tareWeight +
                ", capacity=" + capacity +
                '}';
    }
}
