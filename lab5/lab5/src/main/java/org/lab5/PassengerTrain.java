package org.lab5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Represents a passenger train as an array of passenger cars.
 * Provides operations:
 * <ul>
 *     <li>calculate total number of passengers</li>
 *     <li>calculate total baggage weight</li>
 *     <li>sort cars by comfort level</li>
 *     <li>find cars by passenger count range</li>
 * </ul>
 */
public class PassengerTrain {

    private final PassengerCar[] cars;
    private int size;

    /**
     * Creates a passenger train with a fixed maximum number of cars.
     *
     * @param capacity maximum number of cars in the train
     */
    public PassengerTrain(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Train capacity must be positive");
        }
        this.cars = new PassengerCar[capacity];
        this.size = 0;
    }

    /**
     * Adds a passenger car to the train.
     *
     * @param car car to add
     * @throws RailwayException if the train is already full or car is null
     */
    public void addCar(PassengerCar car) throws RailwayException {
        if (car == null) {
            throw new RailwayException("Car must not be null");
        }
        if (size >= cars.length) {
            throw new RailwayException("Train is full. Cannot add more cars");
        }
        cars[size++] = car;
    }

    /**
     * Returns the number of cars currently in the train.
     *
     * @return number of cars
     */
    public int getCarCount() {
        return size;
    }

    /**
     * Calculates total number of passengers in all cars of the train.
     *
     * @return total passenger count
     */
    public int calculateTotalPassengers() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += cars[i].getCurrentPassengers();
        }
        return total;
    }

    /**
     * Calculates total baggage weight across all cars.
     *
     * @return total baggage weight in kilograms
     */
    public double calculateTotalBaggageWeight() {
        double total = 0.0;
        for (int i = 0; i < size; i++) {
            total += cars[i].getCurrentBaggageWeight();
        }
        return total;
    }

    /**
     * Sorts cars in the train by comfort level (ascending).
     */
    public void sortByComfortLevel() {
        Arrays.sort(
                cars,
                0,
                size,
                Comparator.comparingInt(car -> car.getComfortLevel().getRank())
        );
    }

    /**
     * Finds the first car whose current passenger count lies
     * within the specified inclusive range.
     *
     * @param minPassengers minimum number of passengers (inclusive)
     * @param maxPassengers maximum number of passengers (inclusive)
     * @return found passenger car
     * @throws CarNotFoundException if no such car exists
     */
    public PassengerCar findCarByPassengerRange(int minPassengers, int maxPassengers)
            throws CarNotFoundException {

        if (minPassengers < 0 || maxPassengers < 0 || minPassengers > maxPassengers) {
            throw new IllegalArgumentException("Invalid passenger range");
        }

        for (int i = 0; i < size; i++) {
            int passengers = cars[i].getCurrentPassengers();
            if (passengers >= minPassengers && passengers <= maxPassengers) {
                return cars[i];
            }
        }
        throw new CarNotFoundException(
                "No car found with passengers in range [" + minPassengers + ", " + maxPassengers + "]"
        );
    }

    /**
     * Returns a defensive copy of the internal car array
     * containing only the actual cars in the train.
     *
     * @return array of cars
     */
    public PassengerCar[] getCarsSnapshot() {
        return Arrays.copyOf(cars, size);
    }

    @Override
    public String toString() {
        return "PassengerTrain{" +
                "cars=" + Arrays.toString(getCarsSnapshot()) +
                '}';
    }
}
