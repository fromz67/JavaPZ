package org.lab5;

/**
 * Enum that represents comfort level of a passenger car.
 */
public enum ComfortLevel {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    LUXURY(4);

    private final int rank;

    ComfortLevel(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }
}

