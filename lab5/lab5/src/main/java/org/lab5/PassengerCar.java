package org.lab5;

/**
 * Abstract passenger car that extends generic RollingStock with Integer capacity.
 */
public abstract class PassengerCar extends RollingStock<Integer> {

    private final ComfortLevel comfortLevel;
    private final double maxBaggageWeight; // in kilograms

    private int currentPassengers;
    private double currentBaggageWeight;

    /**
     * Creates a new passenger car.
     *
     * @param id                 unique identifier
     * @param tareWeight         empty weight (tons)
     * @param passengerCapacity  maximum number of passengers
     * @param comfortLevel       comfort level of the car
     * @param maxBaggageWeight   maximum baggage weight (kg)
     */
    protected PassengerCar(
            String id,
            double tareWeight,
            int passengerCapacity,
            ComfortLevel comfortLevel,
            double maxBaggageWeight
    ) {
        super(id, tareWeight, passengerCapacity);
        if (comfortLevel == null) {
            throw new IllegalArgumentException("Comfort level must not be null");
        }
        if (maxBaggageWeight < 0) {
            throw new IllegalArgumentException("Max baggage weight must be non-negative");
        }
        this.comfortLevel = comfortLevel;
        this.maxBaggageWeight = maxBaggageWeight;
    }

    /**
     * Adds passengers to the car.
     *
     * @param passengers number of passengers to add
     * @throws CapacityExceededException if capacity would be exceeded
     */
    public void addPassengers(int passengers) throws CapacityExceededException {
        if (passengers < 0) {
            throw new IllegalArgumentException("Passengers to add must be non-negative");
        }
        int newCount = currentPassengers + passengers;
        if (newCount > getCapacity()) {
            throw new CapacityExceededException(
                    "Cannot add " + passengers + " passengers. Capacity: "
                            + getCapacity() + ", current: " + currentPassengers
            );
        }
        currentPassengers = newCount;
    }

    /**
     * Adds baggage weight to the car.
     *
     * @param weight baggage weight in kilograms
     * @throws CapacityExceededException if baggage limit would be exceeded
     */
    public void addBaggage(double weight) throws CapacityExceededException {
        if (weight < 0) {
            throw new IllegalArgumentException("Baggage weight must be non-negative");
        }
        double newWeight = currentBaggageWeight + weight;
        if (newWeight > maxBaggageWeight) {
            throw new CapacityExceededException(
                    "Cannot add baggage " + weight + " kg. Capacity: "
                            + maxBaggageWeight + " kg, current: " + currentBaggageWeight + " kg"
            );
        }
        currentBaggageWeight = newWeight;
    }

    /**
     * Returns current passenger count in this car.
     *
     * @return passenger count
     */
    public int getCurrentPassengers() {
        return currentPassengers;
    }

    /**
     * Returns current baggage weight in this car.
     *
     * @return baggage weight in kilograms
     */
    public double getCurrentBaggageWeight() {
        return currentBaggageWeight;
    }

    /**
     * Returns comfort level of this car.
     *
     * @return comfort level enum value
     */
    public ComfortLevel getComfortLevel() {
        return comfortLevel;
    }

    /**
     * Returns maximum baggage weight of this car.
     *
     * @return baggage capacity in kilograms
     */
    public double getMaxBaggageWeight() {
        return maxBaggageWeight;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id='" + getId() + '\'' +
                ", comfortLevel=" + comfortLevel +
                ", passengerCapacity=" + getCapacity() +
                ", currentPassengers=" + currentPassengers +
                ", maxBaggageWeight=" + maxBaggageWeight +
                ", currentBaggageWeight=" + currentBaggageWeight +
                '}';
    }
}
