package org.lab5;

/**
 * Economy-class passenger car with low comfort level.
 */
public class EconomyPassengerCar extends PassengerCar {

    public EconomyPassengerCar(String id, double tareWeight, int passengerCapacity, double maxBaggageWeight) {
        super(id, tareWeight, passengerCapacity, ComfortLevel.LOW, maxBaggageWeight);
    }
}
