package org.lab5;

/**
 * Luxury passenger car with the highest comfort level.
 */
public class LuxuryPassengerCar extends PassengerCar {

    public LuxuryPassengerCar(String id, double tareWeight, int passengerCapacity, double maxBaggageWeight) {
        super(id, tareWeight, passengerCapacity, ComfortLevel.LUXURY, maxBaggageWeight);
    }
}

