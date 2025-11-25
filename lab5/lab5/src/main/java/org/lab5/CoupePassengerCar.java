package org.lab5;
/**
 * Coupe-class passenger car with medium comfort level.
 */
public class CoupePassengerCar extends PassengerCar {

    public CoupePassengerCar(String id, double tareWeight, int passengerCapacity, double maxBaggageWeight) {
        super(id, tareWeight, passengerCapacity, ComfortLevel.MEDIUM, maxBaggageWeight);
    }
}
