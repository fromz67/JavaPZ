package org.lab6;

public abstract class RollingStock<T extends Number> {

    private final String id;
    private final T capacity;

    /**
     * Creates a new rolling stock instance.
     *
     * @param id       unique identifier (must not be null or blank)
     * @param capacity capacity value (must not be null and non-negative)
     * @throws IllegalArgumentException if arguments are invalid
     */
    protected RollingStock(String id, T capacity) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID must not be null or blank");
        }
        if (capacity == null || capacity.doubleValue() < 0) {
            throw new IllegalArgumentException("Capacity must be non-negative and not null");
        }
        this.id = id;
        this.capacity = capacity;
    }

    /**
     * Returns the identifier of this rolling stock.
     *
     * @return identifier string
     */
    public String getId() {
        return id;
    }

    /**
     * Returns capacity value of this rolling stock.
     *
     * @return capacity
     */
    public T getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id='" + id + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
