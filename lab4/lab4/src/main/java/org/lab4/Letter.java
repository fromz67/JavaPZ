package org.lab4;

/**
 * Represents a single letter (Unicode code unit) of a word.
 */
public final class Letter {
    private final char value;

    public Letter(char value) {
        this.value = value;
    }

    public char value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Letter)) {
            return false;
        }
        Letter other = (Letter) obj;
        return value == other.value;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(value);
    }
}
